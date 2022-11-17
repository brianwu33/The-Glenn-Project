package com.brian.theglennprojectapi.commandlinerunner;

import com.brian.theglennprojectapi.entity.Activity;
import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ActivityCommandLineRunner implements CommandLineRunner {
    private ActivityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //Date date1 = Date.valueOf(LocalDate.of(2002, 03, 03));
        LocalDateTime startTime = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
        LocalDateTime endTime = LocalDateTime.of(2015, Month.JULY, 29, 20, 30, 40);

        //repository.save(new Activity("Badminton", "1", "CIF", startTime, endTime, "aaa", "Be careful", 15));
        //repository.save(new Activity("Basketball", "2", "CIF", startTime, endTime, "bbb","Be careful",  20));

        List<Activity> activities = repository.findAll();
        Logger logger = LoggerFactory.getLogger(getClass());
        //users.forEach(user -> logger.info(user.toString()));
        System.out.println(activities);
        activities.forEach(activity -> System.out.println(activity.toString()));
    }
}
