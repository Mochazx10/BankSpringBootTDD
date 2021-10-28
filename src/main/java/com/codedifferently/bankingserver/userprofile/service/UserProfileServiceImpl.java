package com.codedifferently.bankingserver.userprofile.service;

import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileCreationException;
import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileNotFoundException;
import com.codedifferently.bankingserver.userprofile.models.UserProfile;
import com.codedifferently.bankingserver.userprofile.repos.UserProfileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    private UserProfileRepo repo;
    private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Autowired
    public UserProfileServiceImpl(UserProfileRepo repo){
        this.repo = repo;
    }

    @Override
    public UserProfile createUserProfile(UserProfile userProfile) throws UserProfileCreationException {
        String email = userProfile.getEmail();
        Optional<UserProfile> userProfileOptional = repo.findByEmail(email);
        if(userProfileOptional.isPresent()){
            logger.error("User could not be created because email {} already exists", email);
            throw new UserProfileCreationException("User Exists");
        }
        userProfile = repo.save(userProfile);
        logger.info("Created User with id {} and email {}", userProfile.getId(), userProfile.getEmail());
        return userProfile;
    }

    @Override
    public List<UserProfile> getAllProfiles() {
        return (List<UserProfile>)repo.findAll();
    }

    @Override
    public UserProfile getProfileById(Integer id) throws UserProfileNotFoundException {
        Optional<UserProfile> userProfileOptional = repo.findById(id);
        if(userProfileOptional.isEmpty()){
            logger.error("User could not be updated because id {} does not exist", id);
            throw new UserProfileNotFoundException();
        }
        return userProfileOptional.get();
    }

    @Override
    public UserProfile getProfileByEmail(String email) throws UserProfileCreationException {
        return null;
    }

    @Override
    public Boolean updateUserProfile(Integer id, UserProfile userProfile) throws UserProfileNotFoundException {
        return null;
    }

    @Override
    public Boolean deleteUserProfile(Integer id) throws UserProfileNotFoundException {
        return null;
    }
}
