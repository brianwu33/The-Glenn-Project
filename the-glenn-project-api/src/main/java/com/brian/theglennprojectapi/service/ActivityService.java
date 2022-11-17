package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.ActivityRequestDTO;
import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ActivityResponseDTO createActivity(ActivityRequestDTO activityRequestDTO) {
        Optional<UserDetails> owner = userDetailsRepository.findById(activityRequestDTO.getOwnerId());
        if(owner.isEmpty()){
            return null;
        }
        Activity newActivity = modelMapper.map(activityRequestDTO, Activity.class);
        newActivity.getParticipants().add(owner.get());
        ActivityResponseDTO response = modelMapper.map(activityRepository.save(newActivity), ActivityResponseDTO.class);
        return response;
    }

    public List<ActivityResponseDTO> retrieveAllActivities() {
        return activityRepository.findAll().stream().map(activity -> modelMapper.map(activity, ActivityResponseDTO.class)).collect(Collectors.toList());
    }

    public ActivityResponseDTO retrieveActivityById(Long activityId) {
        Optional<Activity> activity = activityRepository.findById(activityId);
        if(activity.isEmpty()){
            return null;
        }
        ActivityResponseDTO response = modelMapper.map(activity, ActivityResponseDTO.class);
        return response;
    }
}
