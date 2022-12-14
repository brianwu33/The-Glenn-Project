package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
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
        List<UserDetailsResponseDTO> userList = userDetailsRepository.findAll().stream().map(user -> modelMapper.map(user, UserDetailsResponseDTO.class)).collect(Collectors.toList());
        return userList;
    }

    public UserDetailsResponseDTO retrieveUserById(Long userId) throws UserNotFoundException {
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found with Id: " + userId);
        }
        UserDetailsResponseDTO response = modelMapper.map(user, UserDetailsResponseDTO.class);
        return response;
    }

    public UserDetailsResponseDTO createUser(UserDetailsRequestDTO userDetailsRequestDTO){
        UserDetails newUser = modelMapper.map(userDetailsRequestDTO, UserDetails.class);
        UserDetailsResponseDTO response = modelMapper.map(userDetailsRepository.save(newUser), UserDetailsResponseDTO.class);
        return response;
    }

    public UserDetailsResponseDTO updateUserById(Long userId, UserDetailsRequestDTO userDetailsRequestDTO) {
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        UserDetails newUser = modelMapper.map(userDetailsRequestDTO, UserDetails.class);
        newUser.setId(userId);
        UserDetailsResponseDTO response = modelMapper.map(userDetailsRepository.save(newUser), UserDetailsResponseDTO.class);
        return response;
    }

    public UserDetailsResponseDTO deleteUserById(Long userId) {
        //When delete a User
        //Step 1. Remove him from all the activities they joined
        //Step 2. Delete all the activities created.
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        UserDetails userDetails = user.get();
        //Step 1
        for(Activity activityJoined : userDetails.getJoinedActivities()){
            activityService.deleteActivityParticipants(activityJoined.getId(), userDetails.getId());
        }
        //Step 2
        for(Activity activity : activityRepository.findAll()){
            if(activity.getOwnerId() == userId){
                activityService.deleteActivityById(activity.getId());
            }
        }

        userDetailsRepository.deleteById(userId);
        UserDetailsResponseDTO response = modelMapper.map(userDetails, UserDetailsResponseDTO.class);
        return response;
    }


    public List<ActivityResponseDTO> retrieveJoinedActivityByUserId(Long userId) {
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        List<ActivityResponseDTO> activityResponseDTOList = new ArrayList<>();
        Set<Activity> activitySet= user.get().getJoinedActivities();
        for(Activity activity : activitySet){
            ActivityResponseDTO activityResponseDTO = modelMapper.map(activity, ActivityResponseDTO.class);
            activityResponseDTOList.add(activityResponseDTO);
        }
        return activityResponseDTOList;
    }

    public List<ActivityResponseDTO> retrieveCreatedActivityByUserId(Long userId) {
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        List<Activity> activities = activityRepository.findByOwnerId(userId);
        List<ActivityResponseDTO> activityResponseDTOList = activities.stream().map(activity -> modelMapper.map(activity, ActivityResponseDTO.class)).collect(Collectors.toList());
        return activityResponseDTOList;
    }
}
