package com.brian.theglennprojectapi.controller;

import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserDetailsController {
    @Autowired
    UserDetailService userDetailService;

    @GetMapping()
    public ResponseEntity<List<UserDetails>> retrieveAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.retrieveAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetails> retrieveUserById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.retrieveUserById(userId));
    }
    @PostMapping()
    public ResponseEntity<UserDetails> createUser(@RequestBody UserDetailsRequestDTO userDetailsRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.createUser(userDetailsRequestDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDetails> updateUserById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailService.updateUserById(userId));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDetails> deleteUserById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userDetailService.deleteUserById(userId));
    }
}
