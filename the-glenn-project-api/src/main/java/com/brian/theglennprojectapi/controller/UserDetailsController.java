package com.brian.theglennprojectapi.controller;

import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserDetailsController {
    @Autowired
    UserDetailService userService;

    public List<UserDetails> retrieveAllUsers(){
        return null;
    }

}
