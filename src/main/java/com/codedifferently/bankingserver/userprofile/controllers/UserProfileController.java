package com.codedifferently.bankingserver.userprofile.controllers;

import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileCreationException;
import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileNotFoundException;
import com.codedifferently.bankingserver.userprofile.models.UserProfile;
import com.codedifferently.bankingserver.userprofile.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserProfileController {
    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
    private UserProfileService service;

    @Autowired
    public UserProfileController(UserProfileService service){
        this.service = service;
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles(){
        List<UserProfile> profiles = service.getAllProfiles();
        ResponseEntity<List<UserProfile>> response = new ResponseEntity<>(profiles, HttpStatus.OK);
        return response;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Integer id){
        try {
            UserProfile userProfile = service.getProfileById(id);
            ResponseEntity<?> response = new ResponseEntity<>(userProfile, HttpStatus.OK);
            return response;
        }catch (UserProfileNotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();        }
    }

    // Todo : Get Mapping by Email

    @PostMapping("/profile")
    public ResponseEntity<?> createUserProfile(@RequestBody UserProfile userProfile){
        try {
            UserProfile savedProfile = service.createUserProfile(userProfile);
            logger.info("{}",savedProfile.getId());
            ResponseEntity<?> response = new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
            return response;
        }catch(UserProfileCreationException ex){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Integer id, @RequestBody UserProfile userProfile){
        try {
            service.updateUserProfile(id, userProfile);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }catch(UserProfileNotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    // Todo : Complete Delete

}
