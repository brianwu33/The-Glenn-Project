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
        Optional<Activity> activity = activityRepository.findById(activityId);
        if(activity.isEmpty()){
            throw new ActivityNotFoundException("Activity Not Found with Id: " + activityId);
        }
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
        Optional<Activity> activity = activityRepository.findById(activityId);
        if(activity.isEmpty()){
            throw new ActivityNotFoundException("Activity Not Found with Id: " + activityId);
        }
        activity.get().setName(activityRequestDTO.getName());
        activity.get().setLink(activityRequestDTO.getLink());
        activity.get().setLocation(activityRequestDTO.getLocation());
        activity.get().setStartAt(activityRequestDTO.getStartAt());
        activity.get().setEndAt(activityRequestDTO.getEndAt());
        activity.get().setAdditionalInfo(activityRequestDTO.getAdditionalInfo());
        activity.get().setMaximumParticipants(activityRequestDTO.getMaximumParticipants());
        return modelMapper.map(activityRepository.save(activity.get()), ActivityResponseDTO.class);
    }

    public ActivityResponseDTO deleteActivityById(Long activityId) {
        Optional<Activity> activity = activityRepository.findById(activityId);
        if(activity.isEmpty()){
            throw new ActivityNotFoundException("Activity Not Found with Id: " + activityId);
        }
        activityRepository.deleteById(activityId);
        ActivityResponseDTO response = modelMapper.map(activity, ActivityResponseDTO.class);
        return response;
    }

    public ActivityResponseDTO addActivityParticipants(Long activityId, Long userId) throws Exception{
        Optional<Activity> activity = activityRepository.findById(activityId);
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(activity.isEmpty()){
            throw new ActivityNotFoundException("Activity Not Found with Id: " + activityId);
        }
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found with Id: " + userId);
        }
        if(activity.get().getParticipants().contains(user.get())){
            throw new Exception("User Already Exist in Activity");
        }
        activity.get().getParticipants().add(user.get());
        Activity newActivity = activityRepository.save(activity.get());
        return modelMapper.map(newActivity, ActivityResponseDTO.class);
    }

    public ActivityResponseDTO deleteActivityParticipants(Long activityId, Long userId) throws Exception{
        Optional<Activity> activity = activityRepository.findById(activityId);
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(activity.isEmpty()){
            throw new ActivityNotFoundException("Activity Not Found with Id: " + activityId);
        }
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found with Id: " + userId);
        }
        //check if the user is in the list
        if(!activity.get().getParticipants().contains(user.get())){
            throw new Exception("User Does Not Exist in Activity");
        }
        activity.get().getParticipants().remove(user.get());
        Activity newActivity = activityRepository.save(activity.get());
        return modelMapper.map(newActivity, ActivityResponseDTO.class);

    }
}
