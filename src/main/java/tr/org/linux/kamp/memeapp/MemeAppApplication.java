package tr.org.linux.kamp.memeapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tr.org.linux.kamp.memeapp.memes.Meme;
import tr.org.linux.kamp.memeapp.memes.MemeService;
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
	public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}

    @Bean
    CommandLineRunner myMethod(UserService userService, MemeService memeService, PasswordEncoder passwordEncoder) {
        return args -> {
        	if (!userService.existsByUsername("username0")) {
				for (int i = 0; i < 10; i++) {
					String username = "username" + i;
					String email = "user" + i + "@mailinator.com";
					String firstName = "First" + i;
					String lastName = "Doe";
					User user = new User(username, email, firstName, lastName);
					user.setPassword(passwordEncoder.encode("password"));
					userService.save(user);

					for (int j = 0; j < 50; j++) {
						String name = "meme name " + j;
						String description = "some description here";
						String artist = "Artist " + j;
						String url = "https://picsum.photos/500/500?image=" + j;
						Meme meme = new Meme(name, description, artist, url, user);
						memeService.save(meme);
					}

				}
			}
        };
    }

}
