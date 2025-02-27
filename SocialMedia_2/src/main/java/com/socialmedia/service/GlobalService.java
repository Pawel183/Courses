package com.socialmedia.service;

import com.socialmedia.models.SocialUser;
import com.socialmedia.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalService {
    @Autowired
    public SocialUserRepository userRepository;

    public List<SocialUser> getAllUsers() {
        return userRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        return userRepository.save(socialUser);
    }
}
