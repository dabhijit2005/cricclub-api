package com.cricclub.service;

import com.cricclub.model.UserProfile;
import com.cricclub.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile findByEmail(String email) {
        return userProfileRepository.findUserProfileByEmail(email);
    }
}
