package com.brian.theglennprojectapi.commandlinerunner;

import com.brian.theglennprojectapi.entity.UserDetails;
import com.brian.theglennprojectapi.repository.UserDetailsRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
    private UserDetailsRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //Date date1 = Date.valueOf(LocalDate.of(2002, 03, 03));
        LocalDate date1 = LocalDate.of(2002, 3, 3);
        LocalDate date2 = LocalDate.of(2000, 10, 25);
        LocalDate date3 = LocalDate.of(1999, 5, 5);

        repository.save(new UserDetails("brianwu20020303@gmail.com", "University of Waterloo","Brian", "Wu", "male", date1));
        repository.save(new UserDetails("gabreil@gmail.com", "University of Waterloo", "Gabriel", "Diniz", "male", date2));
        repository.save(new UserDetails("steven@gmail.com", "University of Waterloo", "Steven", "Wang", "male", date3));

        List<UserDetails> users = repository.findAll();
        Logger logger = LoggerFactory.getLogger(getClass());
        //users.forEach(user -> logger.info(user.toString()));
        System.out.println(users);
        users.forEach(user -> System.out.println(user.toString()));
    }
}
