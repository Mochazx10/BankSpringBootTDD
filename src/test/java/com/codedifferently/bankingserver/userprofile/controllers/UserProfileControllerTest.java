package com.codedifferently.bankingserver.userprofile.controllers;


import com.codedifferently.bankingserver.userprofile.exceptions.UserProfileNotFoundException;
import com.codedifferently.bankingserver.userprofile.models.UserProfile;
import com.codedifferently.bankingserver.userprofile.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    @MockBean
    private UserProfileService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /profile/1 - Found")
    public void getProfileByIdSuccessTest() throws Exception{
        UserProfile mockUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);
        mockUserProfile.setId(1);

        doReturn(mockUserProfile).when(service).getProfileById(1);

        mockMvc.perform(get("/profile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Tariq")));
    }

    @Test
    @DisplayName("GET /profile/1 - Not Found")
    public void getProfileByIdFailedTest() throws Exception{
        doThrow(new UserProfileNotFoundException()).when(service).getProfileById(1);
        mockMvc.perform(get("/profile/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /profile - success")
    public void createUserProfileTestSuccess() throws Exception{
        UserProfile postUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);

        UserProfile mockUserProfile = new UserProfile("Tariq", "DaBaron", "tariq@leaveMeAlone.com", 43);
        mockUserProfile.setId(1);

        doReturn(mockUserProfile).when(service).createUserProfile(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postUserProfile)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("Tariq")));

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
