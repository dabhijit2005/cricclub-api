package com.cricclub.controller;

import com.cricclub.model.UserProfile;
import com.cricclub.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserProfileService userProfileService;
    @GetMapping("/validateEmail")
    public ResponseEntity<?> validateUserByEmail(@RequestParam String email){
        UserProfile userProfile = userProfileService.findByEmail(email);
        return userProfile != null ? ResponseEntity.ok().build()  : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
