package com.codedifferently.bankingserver.userprofile.service;

import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileCreationException;
import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileNotFoundException;
import com.codedifferently.bankingserver.userprofile.models.UserProfile;
import com.codedifferently.bankingserver.userprofile.repos.UserProfileRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import  static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserProfileServiceImplTest {

    @Autowired
    private UserProfileService service;

    @MockBean
    private UserProfileRepo repo;

    @Test
    @DisplayName("Create User Profile Test - Success")
    public void testCreateUserProfileSuccess() throws UserProfileCreationException {
        UserProfile inputUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);

        UserProfile mockUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);
        mockUserProfile.setId(1);

        doReturn(mockUserProfile).when(repo).save(any());
        doReturn(Optional.empty()).when(repo).findByEmail(any());

        UserProfile savedProfile = service.createUserProfile(inputUserProfile);

        Assertions.assertNotNull(savedProfile, "Profile should not be null");
        Assertions.assertEquals(mockUserProfile.toString(), savedProfile.toString(), "Profile Id should be equal to 1");

    }

    @Test
    @DisplayName("Create User Profile Test - Fail")
    public void testCreateUserProfileFail(){

        UserProfile inputUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);

        UserProfile mockUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);
        mockUserProfile.setId(1);

        doReturn(Optional.of(mockUserProfile)).when(repo).findByEmail(any());

        Assertions.assertThrows(UserProfileCreationException.class, ()-> {
            service.createUserProfile(inputUserProfile);
        });
    }

    @Test
    @DisplayName("Get All Profiles")
    public void findAllUserProfilesTest(){
        UserProfile mockUserProfile1 = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);
        mockUserProfile1.setId(1);
        UserProfile mockUserProfile2 = new UserProfile("Tariq", "Hook", "tariq@codedifferently.com", 43);
        mockUserProfile2.setId(2);

        doReturn(Arrays.asList(mockUserProfile1, mockUserProfile2)).when(repo).findAll();

        List<UserProfile> profiles = service.getAllProfiles();

        Assertions.assertEquals(2, profiles.size(), "There should be 2 user profiles");
    }

    @Test
    @DisplayName("Find User Profile By Id - Success")
    public void findUserProfileByIdSuccessTest() throws UserProfileNotFoundException {
        UserProfile mockUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);
        mockUserProfile.setId(1);

        doReturn(Optional.of(mockUserProfile)).when(repo).findById(1);

        UserProfile foundProfile = service.getProfileById(1);

        Assertions.assertSame(mockUserProfile, foundProfile, "Profiles should be Identical");
    }

    @Test
    @DisplayName("Find User Profile by Id - Failed")
    public void findUserProfileByIdFailedTest(){
        doReturn(Optional.empty()).when(repo).findById(1);

        Assertions.assertThrows(UserProfileNotFoundException.class, ()-> {
           service.getProfileById(1);
        });
    }

    // TODO Step 1A: Create Unit Test for get Profile by Email

    // Todo : Update User Profile

    //Todo : Delete Profile
}
