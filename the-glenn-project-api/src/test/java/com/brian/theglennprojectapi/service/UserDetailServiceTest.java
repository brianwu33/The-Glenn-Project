package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.Gender;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;
    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private ActivityService activityService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    UserDetailService userDetailService;

    //Verify if something is call exactly once
    //Mockito.verify(postRepository, Mockito.times(1)).save(ArgumentMatchers.any(Post.class));

    @Test
    @DisplayName("Should throw User Not Found Exception")
    void shouldThrowExceptionWhenUserNotFound(){
        Mockito.when(userDetailsRepository.findById(123L)).thenReturn(Optional.empty());
        //Retrieve User
        UserNotFoundException exception1 = assertThrows(UserNotFoundException.class, () -> {
            userDetailService.retrieveUserById(123L);
        });
        //Update User
        UserNotFoundException exception2 = assertThrows(UserNotFoundException.class, () -> {
            userDetailService.updateUserById(123L, new UserDetailsRequestDTO());
        });
        UserNotFoundException exception3 = assertThrows(UserNotFoundException.class, () -> {
            userDetailService.deleteUserById(123L);
        });
        UserNotFoundException exception4 = assertThrows(UserNotFoundException.class, () -> {
            userDetailService.retrieveJoinedActivityByUserId(123L);
        });
        UserNotFoundException exception5 = assertThrows(UserNotFoundException.class, () -> {
            userDetailService.retrieveCreatedActivityByUserId(123L);
        });
        assertEquals("User Not Found with Id: 123", exception1.getMessage());
        assertEquals("User Not Found with Id: 123", exception2.getMessage());
        assertEquals("User Not Found with Id: 123", exception3.getMessage());
        assertEquals("User Not Found with Id: 123", exception4.getMessage());
        assertEquals("User Not Found with Id: 123", exception5.getMessage());

    }


    @Test
    @DisplayName("Should Find User By Id")
    void shouldFindUser() {
        UserDetails user1 = new UserDetails(123L, "brianwu20020303@gmail.com", "1234567890","University of Waterloo","Brian Wu", Gender.MALE, LocalDate.of(2002, 3, 3), new HashSet<>());
        UserDetailsResponseDTO expectedUserResponse = new UserDetailsResponseDTO(123L, "Brian Wu", "brianwu20020303@gmail.com", "University of Waterloo", "Gender.MALE", LocalDate.of(2002, 3, 3));
        Mockito.when(userDetailsRepository.findById(123L)).thenReturn(Optional.of(user1));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(expectedUserResponse);
        UserDetailsResponseDTO actualUserResponse = userDetailService.retrieveUserById(123L);
        Assertions.assertThat(actualUserResponse.getId()).isEqualTo(expectedUserResponse.getId());
    }


    @Test
    @DisplayName("Should return activities join by the user")
    void retrieveJoinedActivityByUserId() {
        //Create an activity
        Activity activity1 = new Activity();
        activity1.setName("Badminton");
        //Create a user
        UserDetails user1 = new UserDetails(123L, "brianwu20020303@gmail.com", "1234567890","University of Waterloo","Brian Wu", Gender.MALE, LocalDate.of(2002, 3, 3), new HashSet<>());
        user1.getJoinedActivities().add(activity1);
        //Create an activity dto
        ActivityResponseDTO expectedActivityResponseDTO = new ActivityResponseDTO();
        expectedActivityResponseDTO.setName("Badminton");
        //
        Mockito.when(userDetailsRepository.findById(123L)).thenReturn(Optional.of(user1));
        Mockito.when(modelMapper.map(Mockito.any(Activity.class), Mockito.any())).thenReturn(expectedActivityResponseDTO);
        //
        List<ActivityResponseDTO> response = userDetailService.retrieveJoinedActivityByUserId(123L);
        assertEquals(activity1.getName(), response.get(0).getName());
    }

    @Test
    @DisplayName("Should return activities created by the user")
    void retrieveCreatedActivityByUserId() {
        //Create an activity
        Activity activity1 = new Activity();
        activity1.setName("Badminton");
        activity1.setOwnerId(123L);
        List<Activity> list = new ArrayList<>();
        list.add(activity1);
        //Create a user
        UserDetails user1 = new UserDetails(123L, "brianwu20020303@gmail.com", "1234567890","University of Waterloo","Brian Wu", Gender.MALE, LocalDate.of(2002, 3, 3), new HashSet<>());
        //Create the activity dto
        ActivityResponseDTO expectedActivityResponseDTO = new ActivityResponseDTO();
        expectedActivityResponseDTO.setName("Badminton");

        Mockito.when(userDetailsRepository.findById(123L)).thenReturn(Optional.of(user1));
        Mockito.when(activityRepository.findByOwnerId(Mockito.any())).thenReturn(list);
        Mockito.when(modelMapper.map(Mockito.any(Activity.class), Mockito.any())).thenReturn(expectedActivityResponseDTO);
        //
        List<ActivityResponseDTO> response = userDetailService.retrieveCreatedActivityByUserId(123L);
        assertEquals(activity1.getName(), response.get(0).getName());

    }
}