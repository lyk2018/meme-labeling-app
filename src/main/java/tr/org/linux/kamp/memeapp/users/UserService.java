package tr.org.linux.kamp.memeapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    User save(User user) {
        return userRepository.save(user);
    }

    void delete(Long id) {
        userRepository.deleteById(id);
    }

}
