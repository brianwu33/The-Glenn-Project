package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<UserDetails> retrieveAllUsers() {
        return null;
    }

    public UserDetails retrieveUserById(String userId) {
        return null;
    }

    public UserDetails createUser(UserDetailsRequestDTO userDetailsRequestDTO){
        UserDetails newUser = modelMapper.map(userDetailsRequestDTO, UserDetails.class);
        //System.out.println(newUser);
        return userDetailsRepository.save(newUser);
    }

    public UserDetails updateUserById(String userId) {
        return null;
    }

    public UserDetails deleteUserById(String userId) {
        return null;
    }


}
