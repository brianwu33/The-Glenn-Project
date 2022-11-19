package com.brian.theglennprojectapi.dto;

import com.brian.theglennprojectapi.entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponseDTO {
    private Long id;
    private String name;
    private Long ownerId;
    private String location;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String link;
    private String additionalInfo;
    private int maximumParticipants;
    private Set<UserDetailsResponseDTO> participants;
}
