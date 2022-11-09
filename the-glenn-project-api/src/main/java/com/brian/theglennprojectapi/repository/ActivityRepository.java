package com.brian.theglennprojectapi.repository;

import com.brian.theglennprojectapi.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {
}
