package com.brian.theglennprojectapi.controller;

import com.brian.theglennprojectapi.dto.ActivityRequestDTO;
import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.exception.ActivityNotFoundException;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
import com.brian.theglennprojectapi.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Operation(summary = "Retrieve All Activities")
    @GetMapping()
    public ResponseEntity<List<ActivityResponseDTO>> retrieveAllActivities(){
        return ResponseEntity.status(HttpStatus.OK).body(activityService.retrieveAllActivities());
    }

    @Operation(summary = "Create Activity")
    @PostMapping()
    public ResponseEntity<ActivityResponseDTO> createActivity(@RequestBody ActivityRequestDTO activityRequestDTO){
        ActivityResponseDTO response = activityService.createActivity(activityRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Retrieve Activity By ID")
    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponseDTO> retrieveActivityById(@PathVariable Long activityId){
        ActivityResponseDTO response = activityService.retrieveActivityById(activityId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update Activity By ID")
    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityResponseDTO> updateActivityById(@PathVariable Long activityId, @RequestBody ActivityRequestDTO activityRequestDTO){
        ActivityResponseDTO response = activityService.updateActivityById(activityId, activityRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete Activity By ID")
    @DeleteMapping("/{activityId}")
    public ResponseEntity<ActivityResponseDTO> deleteActivityById(@PathVariable Long activityId){
        ActivityResponseDTO response = activityService.deleteActivityById(activityId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Add Activity Participants By ID")
    @PostMapping("/{activityId}/participants/{userId}")
    public ResponseEntity<ActivityResponseDTO> addActivityParticipants(@PathVariable Long activityId, @PathVariable Long userId) throws Exception{
        ActivityResponseDTO response = activityService.addActivityParticipants(activityId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete Activity Participants By ID")
    @DeleteMapping("/{activityId}/participants/{userId}")
    public ResponseEntity<ActivityResponseDTO> deleteActivityParticipants(@PathVariable Long activityId, @PathVariable Long userId) throws Exception{
        ActivityResponseDTO response = activityService.deleteActivityParticipants(activityId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
