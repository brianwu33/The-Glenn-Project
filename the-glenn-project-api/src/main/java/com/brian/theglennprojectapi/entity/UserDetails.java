package com.brian.theglennprojectapi.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String university;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
}
