package com.embarkx.service;


import com.embarkx.model.User;
import com.embarkx.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getAllAddressesByUser(User user);
}
