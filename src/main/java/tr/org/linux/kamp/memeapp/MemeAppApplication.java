package tr.org.linux.kamp.memeapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.org.linux.kamp.memeapp.users.UserRepository;
import tr.org.linux.kamp.memeapp.users.UserService;

@SpringBootApplication
@Slf4j
public class MemeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemeAppApplication.class, args);
    }

    @Bean
    CommandLineRunner myMethod() {
        return args -> {
            log.info("App has started!!!");
            log.info("Line 2");

        };
    }

}
