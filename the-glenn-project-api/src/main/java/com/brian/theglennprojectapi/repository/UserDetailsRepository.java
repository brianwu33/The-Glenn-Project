package com.brian.theglennprojectapi.repository;

import com.brian.theglennprojectapi.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    //UserDetails findById(Long id);
}
