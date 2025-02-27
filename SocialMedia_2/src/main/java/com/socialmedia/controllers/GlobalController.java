package com.socialmedia.controllers;

import com.socialmedia.models.SocialUser;
import com.socialmedia.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GlobalController {

    @Autowired
    private GlobalService globalService;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getUsers() {
        return new ResponseEntity<>(globalService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser) {
        return new ResponseEntity<>(globalService.saveUser(socialUser), HttpStatus.CREATED);
    }
}
