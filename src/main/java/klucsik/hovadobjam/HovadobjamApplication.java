package klucsik.hovadobjam;

import klucsik.hovadobjam.user.User;
import klucsik.hovadobjam.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HovadobjamApplication {

    public static void main(String[] args) {
        SpringApplication.run(HovadobjamApplication.class, args);
    }


    //for testing and seeding data
    private static final Logger log = LoggerFactory.getLogger(HovadobjamApplication.class);

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // save a few users
            userRepository.save(new User("Thiri"));

            // fetch all users
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }
            log.info("");
        };
    }


}
