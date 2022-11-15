package com.brian.theglennprojectapi.commandlinerunner;

import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.Gender;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
    private UserDetailsRepository userDetailsRepository;
    private ActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(getClass());
        LocalDate date1 = LocalDate.of(2002, 3, 3);
        LocalDate date2 = LocalDate.of(2000, 10, 25);
        LocalDate date3 = LocalDate.of(1999, 5, 5);

        userDetailsRepository.save(new UserDetails("brianwu20020303@gmail.com", "University of Waterloo","Brian", "Wu", Gender.MALE, date1));
        userDetailsRepository.save(new UserDetails("gabreil@gmail.com", "University of Waterloo", "Gabriel", "Diniz", Gender.MALE, date2));
        userDetailsRepository.save(new UserDetails("steven@gmail.com", "University of Waterloo", "Steven", "Wang", Gender.MALE, date3));

        LocalDateTime startTime = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
        LocalDateTime endTime = LocalDateTime.of(2015, Month.JULY, 29, 20, 30, 40);

        Activity activity1 = new Activity("Badminton", "1", "CIF", startTime, endTime, "aaa", 15);
        Activity activity2 = new Activity("Basketball", "2", "CIF", startTime, endTime, "bbb", 20);
        activityRepository.save(activity1);
        activityRepository.save(activity2);

        UserDetails user1 = userDetailsRepository.findById(1l).get();
        user1.getCreatedActivities().addAll(Arrays.asList(activity1, activity2));
        userDetailsRepository.save(user1);

        List<UserDetails> users = userDetailsRepository.findAll();
        //users.forEach(user -> logger.info(user.toString()));
        users.forEach(user -> System.out.println(user.toString()));

        List<Activity> activities = activityRepository.findAll();
        //users.forEach(user -> logger.info(user.toString()));
        activities.forEach(activity -> System.out.println(activity.toString()));
    }
}






















