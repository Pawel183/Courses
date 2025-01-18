package com.media.service;

import com.media.model.SocialUser;
import com.media.repo.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {

    @Autowired
    private SocialUserRepository repo;

    public List<SocialUser> getAllUsers() {
        return repo.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        return repo.save(socialUser);
    }
}
