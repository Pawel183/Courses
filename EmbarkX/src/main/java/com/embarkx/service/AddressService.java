package com.embarkx.service;


import com.embarkx.model.User;
import com.embarkx.payload.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);
}
