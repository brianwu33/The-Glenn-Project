package com.brian.theglennprojectapi.controller;

import com.brian.theglennprojectapi.dto.ActivityResponseDTO;
import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.service.UserDetailService;
import com.brian.theglennprojectapi.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Retrieve All Users")
    @GetMapping()
    public ResponseEntity<List<UserDetailsResponseDTO>> retrieveAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.retrieveAllUsers());
    }

    @Operation(summary = "Retrieve User By ID")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> retrieveUserById(@PathVariable Long userId){
        UserDetailsResponseDTO user = userDetailService.retrieveUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @Operation(summary = "Create User")

    @PostMapping()
    public ResponseEntity<UserDetailsResponseDTO> createUser(@RequestBody UserDetailsRequestDTO userDetailsRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailService.createUser(userDetailsRequestDTO));
    }

    @Operation(summary = "Update User By ID")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> updateUserById(@PathVariable Long userId, @RequestBody UserDetailsRequestDTO userDetailsRequestDTO){
        UserDetailsResponseDTO user = userDetailService.updateUserById(userId, userDetailsRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @Operation(summary = "Delete User By ID")
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDTO> deleteUserById(@PathVariable Long userId) throws Exception{
        UserDetailsResponseDTO user = userDetailService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Retrieve all joined activities By User ID")
    @GetMapping("/{userId}/activities/joined-activities")
    public ResponseEntity<List<ActivityResponseDTO>> retrieveJoinedActivityByUserId(@PathVariable Long userId){
        List<ActivityResponseDTO> activities = userDetailService.retrieveJoinedActivityByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @Operation(summary = "Retrieve all created activities By User ID")
    public ResponseEntity<List<ActivityResponseDTO>> retrieveCreatedActivityByUserId(@PathVariable Long userId){
        List<ActivityResponseDTO> activities = userDetailService.retrieveCreatedActivityByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }
}
