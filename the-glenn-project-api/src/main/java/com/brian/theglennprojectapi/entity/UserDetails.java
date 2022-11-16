package com.brian.theglennprojectapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDetails extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "university")
    private String university;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_activities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName="id", nullable = false, updatable = false))
    private Set<Activity> createdActivities = new HashSet<>();

    public UserDetails(String email, String password, String university, String name, Gender gender, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.university = university;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
//                ", createdActivities=" + createdActivities +
                '}';
    }
}
