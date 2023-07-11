package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.ActivityRequestDTO;
import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.exception.ActivityNotFoundException;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;
    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    private ActivityService activityService;

    @Test
    @DisplayName("Should throw Activity Not Found Exception")
    void shouldThrowExceptionWhenUserNotFound(){
        Mockito.when(activityRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        //Retrieve User
        ActivityNotFoundException exception1 = assertThrows(ActivityNotFoundException.class, () -> {
            activityService.retrieveActivityById(123L);
        });
        //Update User
        ActivityNotFoundException exception2 = assertThrows(ActivityNotFoundException.class, () -> {
            activityService.updateActivityById(123L, new ActivityRequestDTO());
        });
        ActivityNotFoundException exception3 = assertThrows(ActivityNotFoundException.class, () -> {
            activityService.deleteActivityById(123L);
        });
        ActivityNotFoundException exception4 = assertThrows(ActivityNotFoundException.class, () -> {
            activityService.addActivityParticipants(123L, 123L);
        });
        ActivityNotFoundException exception5 = assertThrows(ActivityNotFoundException.class, () -> {
            activityService.deleteActivityParticipants(123L, 123L);
        });
        assertEquals("Activity Not Found with Id: 123", exception1.getMessage());
        assertEquals("Activity Not Found with Id: 123", exception2.getMessage());
        assertEquals("Activity Not Found with Id: 123", exception3.getMessage());
        assertEquals("Activity Not Found with Id: 123", exception4.getMessage());
        assertEquals("Activity Not Found with Id: 123", exception5.getMessage());

    }


    @Test
    void retrieveActivityById() {
        Activity activity = new Activity();
        activity.setName("Badminton");
        Mockito.when(activityRepository.findById(Mockito.any())).thenReturn(Optional.of(activity));
        ActivityResponseDTO actualActivityResponse = activityService.retrieveActivityById(123L);
        Assertions.assertThat(actualActivityResponse.getName()).isEqualTo(activity.getName());
    }



    @Test
    void addActivityParticipants() throws Exception{
        UserDetails user = new UserDetails();
        user.setName("Brian");
        Activity activity = new Activity();
        activity.setName("Badminton");
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(activityRepository.findById(Mockito.any())).thenReturn(Optional.of(activity));
        Mockito.when(activityRepository.save(Mockito.any(Activity.class))).thenAnswer(i -> i.getArguments()[0]);
        ActivityResponseDTO response = activityService.addActivityParticipants(123L, 123L);
        assertFalse(response.getParticipants().isEmpty());
    }

    @Test
    void deleteActivityParticipants() throws Exception{
        UserDetails user = new UserDetails();
        user.setName("Brian");
        Activity activity = new Activity();
        activity.setName("Badminton");
        activity.getParticipants().add(user);
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(activityRepository.findById(Mockito.any())).thenReturn(Optional.of(activity));
        Mockito.when(activityRepository.save(Mockito.any(Activity.class))).thenAnswer(i -> i.getArguments()[0]);
        ActivityResponseDTO response = activityService.deleteActivityParticipants(123L, 123L);
        assertTrue(response.getParticipants().isEmpty());
    }
}