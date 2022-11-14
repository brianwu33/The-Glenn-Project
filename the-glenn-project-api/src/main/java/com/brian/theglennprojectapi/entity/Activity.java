package com.brian.theglennprojectapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity extends BaseEntity{

    @Column(name = "activity_name")
    private String name;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "location")
    private String location;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "link")
    private String link;

    @Column(name = "maximum_participants")
    private int maximumParticipants;

    public Activity(String name, String ownerId, String location, LocalDateTime startAt, LocalDateTime endAt, String link, int maximumParticipants) {
        this.name = name;
        this.ownerId = ownerId;
        this.location = location;
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
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
                '}';
    }
}
