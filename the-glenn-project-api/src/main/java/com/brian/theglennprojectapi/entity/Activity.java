package com.brian.theglennprojectapi.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Activity {
    private String id;
    private String name;
    private String ownerId;
    private String location;
    private LocalDateTime time;
    private LocalTime duration;
    private String link;
    private int maximumParticipants;
    private List<User> participants;
}
