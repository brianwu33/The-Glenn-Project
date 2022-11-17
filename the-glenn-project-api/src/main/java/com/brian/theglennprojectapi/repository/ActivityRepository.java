package com.brian.theglennprojectapi.repository;

import com.brian.theglennprojectapi.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByOwnerId(Long ownerId);
}
