package com.codedifferently.bankingserver.userprofile.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserProfileTest {

    @Test
    @DisplayName("Create user using constructor")
    public void constructorTest(){
        String firstName = "Tariq";
        String lastName = "DaBaron";
        String email = "tariq@leaveMeAlone.com";
        Integer age = 43;

        UserProfile testUser = new UserProfile(firstName,lastName,email,age);
        String expectedUserString = "UserProfile{" +
                "id=null" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
        String actualUserString = testUser.toString();
        Assertions.assertEquals(expectedUserString, actualUserString, "User Strings should be equal");
    }

    @Test
    @DisplayName("Set Age Name")
    public void setFirstNameTest(){
        String firstName = "Tariq";
        String lastName = "DaBaron";
        String email = "tariq@leaveMeAlone.com";
        Integer age = 43;
        Integer newAge = 44;
        UserProfile testUser = new UserProfile(firstName,lastName,email,age);
        testUser.setAge(44);

        String expectedUserString = "UserProfile{" +
                "id=null" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + newAge +
                '}';
        String actualUserString = testUser.toString();
        Assertions.assertEquals(expectedUserString, actualUserString, "User Strings should be equal");
    }

    // Todo: Unit Test for setId
    // Todo: Unit Test for setLastName
    // Todo: Unit Test for setEmail


}
