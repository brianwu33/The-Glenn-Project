package com.brian.theglennprojectapi.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
}
