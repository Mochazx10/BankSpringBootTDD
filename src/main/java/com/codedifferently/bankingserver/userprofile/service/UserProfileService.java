package com.codedifferently.bankingserver.userprofile.service;

import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileCreationException;
import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileNotFoundException;
import com.codedifferently.bankingserver.userprofile.models.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile createUserProfile(UserProfile userProfile) throws UserProfileCreationException;
    List<UserProfile> getAllProfiles();
    UserProfile getProfileById(Integer id) throws UserProfileNotFoundException;
    UserProfile getProfileByEmail(String email) throws UserProfileCreationException;
    Boolean updateUserProfile(Integer id, UserProfile userProfile) throws UserProfileNotFoundException;
    Boolean deleteUserProfile(Integer id) throws UserProfileNotFoundException;
}
