package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.ActivityRequestDTO;
import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.exception.ActivityNotFoundException;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.activation.ActivateFailedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<ActivityResponseDTO> retrieveAllActivities() {
        return activityRepository.findAll().stream().map(activity -> modelMapper.map(activity, ActivityResponseDTO.class)).collect(Collectors.toList());
    }

    public ActivityResponseDTO retrieveActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException("Activity Not Found with Id: " + activityId));
        ActivityResponseDTO response = modelMapper.map(activity, ActivityResponseDTO.class);
        return response;
    }

    public ActivityResponseDTO createActivity(ActivityRequestDTO activityRequestDTO) {
        Long ownerId = activityRequestDTO.getOwnerId();
        Optional<UserDetails> owner = userDetailsRepository.findById(ownerId);
        if(owner.isEmpty()){
            throw new UserNotFoundException("Owner Not Found with Id: " + ownerId);
        }
        Activity newActivity = modelMapper.map(activityRequestDTO, Activity.class);
        newActivity.getParticipants().add(owner.get());
        ActivityResponseDTO response = modelMapper.map(activityRepository.save(newActivity), ActivityResponseDTO.class);
        return response;
    }

    public ActivityResponseDTO updateActivityById(Long activityId, ActivityRequestDTO activityRequestDTO) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException("Activity Not Found with Id: " + activityId));
        activity.setName(activityRequestDTO.getName());
        activity.setLink(activityRequestDTO.getLink());
        activity.setLocation(activityRequestDTO.getLocation());
        activity.setStartAt(activityRequestDTO.getStartAt());
        activity.setEndAt(activityRequestDTO.getEndAt());
        activity.setAdditionalInfo(activityRequestDTO.getAdditionalInfo());
        activity.setMaximumParticipants(activityRequestDTO.getMaximumParticipants());
        return modelMapper.map(activityRepository.save(activity), ActivityResponseDTO.class);
    }

    public ActivityResponseDTO deleteActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException("Activity Not Found with Id: " + activityId));
        activityRepository.deleteById(activityId);
        ActivityResponseDTO response = modelMapper.map(activity, ActivityResponseDTO.class);
        return response;
    }

    public ActivityResponseDTO addActivityParticipants(Long activityId, Long userId) throws Exception{
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException("Activity Not Found with Id: " + activityId));
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));
        if(activity.getParticipants().contains(user)){
            throw new Exception("User Already Exist in Activity");
        }
        activity.getParticipants().add(user);
        Activity updatedActivity = activityRepository.save(activity);
        return modelMapper.map(updatedActivity, ActivityResponseDTO.class);
    }

    public ActivityResponseDTO deleteActivityParticipants(Long activityId, Long userId) throws Exception{
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException("Activity Not Found with Id: " + activityId));
        UserDetails user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found with Id: " + userId));
        //check if the user is in the list
        if(!activity.getParticipants().contains(user)){
            throw new Exception("User Does Not Exist in Activity");
        }
        activity.getParticipants().remove(user);
        Activity updatedActivity = activityRepository.save(activity);
        return modelMapper.map(updatedActivity, ActivityResponseDTO.class);

    }
}
