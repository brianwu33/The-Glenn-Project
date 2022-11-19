package com.brian.theglennprojectapi.controller;

import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserDetailsController {
    @Autowired
    UserDetailService userDetailService;

    @GetMapping()
    public ResponseEntity<List<UserDetailsResponseDTO>> retrieveAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.retrieveAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> retrieveUserById(@PathVariable Long userId){
        UserDetailsResponseDTO user = userDetailService.retrieveUserById(userId);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PostMapping()
    public ResponseEntity<UserDetailsResponseDTO> createUser(@RequestBody UserDetailsRequestDTO userDetailsRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailService.createUser(userDetailsRequestDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> updateUserById(@PathVariable Long userId, @RequestBody UserDetailsRequestDTO userDetailsRequestDTO){
        UserDetailsResponseDTO user = userDetailService.updateUserById(userId, userDetailsRequestDTO);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> deleteUserById(@PathVariable Long userId){
        UserDetailsResponseDTO user = userDetailService.deleteUserById(userId);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{userId}/activities/joined-activities")
    public ResponseEntity<List<ActivityResponseDTO>> retrieveJoinedActivityByUserId(@PathVariable Long userId){
        List<ActivityResponseDTO> activities = userDetailService.retrieveJoinedActivityByUserId(userId);
        if(activities==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @GetMapping("/{userId}/activities/created-activities")
    public ResponseEntity<List<ActivityResponseDTO>> retrieveCreatedActivityByUserId(@PathVariable Long userId){
        List<ActivityResponseDTO> activities = userDetailService.retrieveCreatedActivityByUserId(userId);
        if(activities==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }
}