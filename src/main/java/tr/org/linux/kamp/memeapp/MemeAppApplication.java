package tr.org.linux.kamp.memeapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.org.linux.kamp.memeapp.users.User;
import tr.org.linux.kamp.memeapp.users.UserRepository;
import tr.org.linux.kamp.memeapp.users.UserService;

@SpringBootApplication
@Slf4j
public class MemeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemeAppApplication.class, args);
    }

    @Bean
    CommandLineRunner myMethod(UserService userService) {
        return args -> {
        	if (!userService.existsByUsername("username0")) {
				for (int i = 0; i < 40; i++) {
					String username = "username" + i;
					String email = "user" + i + "@mailinator.com";
					String firstName = "First" + i;
					String lastName = "Doe";
					User user = new User(username, email, firstName, lastName);
					userService.save(user);
				}
			}
        };
    }

}
