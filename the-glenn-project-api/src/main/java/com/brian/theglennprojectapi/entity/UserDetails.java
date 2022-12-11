package com.brian.theglennprojectapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
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

    @ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
    private Set<Activity> joinedActivities = new HashSet<>();

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
                '}';
    }
}
