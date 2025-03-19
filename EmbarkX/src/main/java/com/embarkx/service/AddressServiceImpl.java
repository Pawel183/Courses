package com.embarkx.service;

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
}
