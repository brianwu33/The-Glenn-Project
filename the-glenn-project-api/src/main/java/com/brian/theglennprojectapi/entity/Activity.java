package com.brian.theglennprojectapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity extends BaseEntity{

    @Column(name = "activity_name")
    private String name;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "location")
    private String location;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "link")
    private String link;

    @Column(name = "additional_information")
    private String additionalInfo;

    @Column(name = "maximum_participants")
    private int maximumParticipants;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "activities_users",
            joinColumns = @JoinColumn(name = "activity_id", referencedColumnName="id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName="id", nullable = false, updatable = false))
    private Set<UserDetails> participants = new HashSet<>();

    public Activity(String name, Long ownerId, String location, LocalDateTime startAt, LocalDateTime endAt, String link, String additionalInfo, int maximumParticipants) {
        this.name = name;
        this.ownerId = ownerId;
        this.location = location;
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
        this.additionalInfo = additionalInfo;
        this.maximumParticipants = maximumParticipants;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", name='" + name + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", location='" + location + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", link='" + link + '\'' +
                ", maximumParticipants=" + maximumParticipants +
                ", participants=" + participants +
                '}';
    }
}
