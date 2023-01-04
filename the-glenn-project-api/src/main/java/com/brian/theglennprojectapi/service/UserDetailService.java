package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityService activityService;


    private ModelMapper modelMapper = new ModelMapper();

    //Get all users
    public List<UserDetailsResponseDTO> retrieveAllUsers() {
        return userDetailsRepository.findAll().stream().map(user -> modelMapper.map(user, UserDetailsResponseDTO.class)).collect(Collectors.toList());
    }

    public UserDetailsResponseDTO retrieveUserById(Long userId) {
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));
        return modelMapper.map(user, UserDetailsResponseDTO.class);
    }

    public UserDetailsResponseDTO createUser(UserDetailsRequestDTO userDetailsRequestDTO) {
        UserDetails newUser = modelMapper.map(userDetailsRequestDTO, UserDetails.class);
        return modelMapper.map(userDetailsRepository.save(newUser), UserDetailsResponseDTO.class);
    }

    public UserDetailsResponseDTO updateUserById(Long userId, UserDetailsRequestDTO userDetailsRequestDTO) {
        //Check if provided userId exists
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));
        UserDetails updatedUser = modelMapper.map(userDetailsRequestDTO, UserDetails.class);
        updatedUser.setId(userId);
        UserDetailsResponseDTO response = modelMapper.map(userDetailsRepository.save(updatedUser), UserDetailsResponseDTO.class);
        return response;
    }

    public UserDetailsResponseDTO deleteUserById(Long userId) throws Exception {
        //When delete a User
        //Step 1. Remove him from all the activities they joined
        //Step 2. Delete all the activities created.
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));

        //Step 1
        for (Activity activityJoined : user.getJoinedActivities()) {
            activityService.deleteActivityParticipants(activityJoined.getId(), user.getId());
        }
        //Step 2
        for (Activity activity : activityRepository.findAll()) {
            if (activity.getOwnerId() == userId) {
                activityService.deleteActivityById(activity.getId());
            }
        }

        userDetailsRepository.deleteById(userId);
        UserDetailsResponseDTO response = modelMapper.map(user, UserDetailsResponseDTO.class);
        return response;
    }


    public List<ActivityResponseDTO> retrieveJoinedActivityByUserId(Long userId) {
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));
        List<ActivityResponseDTO> activityResponseDTOList = new ArrayList<>();
        Set<Activity> activitySet = user.getJoinedActivities();
        for (Activity activity : activitySet) {
            ActivityResponseDTO activityResponseDTO = modelMapper.map(activity, ActivityResponseDTO.class);
            activityResponseDTOList.add(activityResponseDTO);
        }
        return activityResponseDTOList;
    }

    public List<ActivityResponseDTO> retrieveCreatedActivityByUserId(Long userId) {
        //Verify user id
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));
        List<Activity> activities = activityRepository.findByOwnerId(userId);
        List<ActivityResponseDTO> activityResponseDTOList = activities.stream().map(activity -> modelMapper.map(activity, ActivityResponseDTO.class)).collect(Collectors.toList());
        return activityResponseDTOList;
    }
}
