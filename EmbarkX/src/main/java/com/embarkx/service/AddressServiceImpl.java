package com.embarkx.service;

import com.embarkx.exceptions.ResourceNotFoundException;
import com.embarkx.model.Address;
import com.embarkx.model.User;
import com.embarkx.payload.AddressDTO;
import com.embarkx.repo.AddressRepository;
import com.embarkx.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setUser(user);

        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        Address createdAddress = addressRepository.save(address);

        return modelMapper.map(createdAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();

        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddressesByUser(User user) {
        List<Address> addressList = user.getAddresses();

        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO updatedAddress(AddressDTO addressDTO, Long addressId) {
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        User user = addressFromDb.getUser();

        addressFromDb.setCity(addressDTO.getCity());
        addressFromDb.setState(addressDTO.getState());
        addressFromDb.setZipCode(addressDTO.getZipCode());
        addressFromDb.setCountry(addressDTO.getCountry());
        addressFromDb.setStreet(addressDTO.getStreet());
        addressFromDb.setBuildingName(addressDTO.getBuildingName());
        addressFromDb.setUser(user);

        user.getAddresses().removeIf(address -> address.getId().equals(addressId));
        user.getAddresses().add(addressFromDb);

        Address updatedAddress = addressRepository.save(addressFromDb);

        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        User user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getId().equals(addressId));

        addressRepository.delete(addressFromDb);

        return "Address deleted successfully with addressId: " + addressId;
    }
}
