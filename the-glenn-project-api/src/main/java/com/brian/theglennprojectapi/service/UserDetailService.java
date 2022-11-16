package com.brian.theglennprojectapi.service;

import com.brian.theglennprojectapi.dto.UserDetailsRequestDTO;
import com.brian.theglennprojectapi.dto.UserDetailsResponseDTO;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private ModelMapper modelMapper = new ModelMapper();

    //Get all users
    public List<UserDetailsResponseDTO> retrieveAllUsers() {
        List<UserDetailsResponseDTO> userList = userDetailsRepository.findAll().stream().map(user -> modelMapper.map(user, UserDetailsResponseDTO.class)).collect(Collectors.toList());
        return userList;
    }

    public UserDetailsResponseDTO retrieveUserById(Long userId) {
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            return null;
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
        Optional<UserDetails> user = userDetailsRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        userDetailsRepository.deleteById(userId);
        UserDetailsResponseDTO response = modelMapper.map(user, UserDetailsResponseDTO.class);
        return response;
    }


}
