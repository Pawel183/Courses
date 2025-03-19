package com.embarkx.controller;

import com.embarkx.model.User;
import com.embarkx.payload.AddressDTO;
import com.embarkx.service.AddressService;
import com.embarkx.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(
            @Valid @RequestBody AddressDTO addressDTO
    ) {
        User user = authUtil.loggedInUser();
        AddressDTO createdAddressDTO = addressService.createAddress(addressDTO, user);

        return new ResponseEntity<>(createdAddressDTO, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addressList = addressService.getAllAddresses();

        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(
            @PathVariable Long addressId
    ) {
        AddressDTO addressDTO = addressService.getAddressById(addressId);

        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @GetMapping("/user/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddressesByUser() {
        User user = authUtil.loggedInUser();

        List<AddressDTO> addressDTOs = addressService.getAllAddressesByUser(user);

        return new ResponseEntity<>(addressDTOs, HttpStatus.OK);
    }
}
