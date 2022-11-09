package com.brian.theglennprojectapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Activity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    //the id of the user who created this activity
    private UUID ownerId;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String link;
    private int maximumParticipants;
    //private List<User> participants;
}
