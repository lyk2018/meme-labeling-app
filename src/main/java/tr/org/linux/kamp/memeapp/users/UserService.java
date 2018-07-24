package tr.org.linux.kamp.memeapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.org.linux.kamp.memeapp.exceptions.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Long id) {
        final Optional<User> userOptional = userRepository.findById(id);
        final User user = userOptional.orElseThrow(ResourceNotFoundException::new);
        user.getMemes().isEmpty();
        return user;
    }

    User save(User user) {
        return userRepository.save(user);
    }

    void delete(Long id) {
        userRepository.deleteById(id);
    }

}
