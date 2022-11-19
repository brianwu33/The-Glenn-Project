package com.brian.theglennprojectapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String university;
    private String gender;
    private LocalDate dateOfBirth;
}
