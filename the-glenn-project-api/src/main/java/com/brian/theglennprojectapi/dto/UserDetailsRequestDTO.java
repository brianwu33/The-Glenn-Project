package com.brian.theglennprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsRequestDTO {

    // email should be a valid email format
    // email should not be null or empty
    @JsonProperty(required = true)
    @NotEmpty
    @Email
    private String email;

    // password should not be null or empty
    // password should have at least 8 characters
    @JsonProperty(required = true)
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    // university should not be null or empty
    @JsonProperty(required = true)
    @NotEmpty
    private String university;

    // name should not be null or empty
    // name should have at least 2 characters
    @JsonProperty(required = true)
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;

    // gender should not be null or empty
    @JsonProperty(required = true)
    @NotEmpty
    private String gender;

    // date of birth should not be null or empty
    // date of birth should be in the past
    @JsonProperty(required = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotEmpty
    @Past
    private LocalDate dateOfBirth;
}

