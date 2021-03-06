package tr.org.linux.kamp.memeapp.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.org.linux.kamp.memeapp.exceptions.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    Iterable<User> findAll() {
        return userRepository.findAll();
    }

    Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        final Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("user not found by: " + username));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        final User persistedUser = this.findById(user.getId());

        if (!persistedUser.getUsername().equals(user.getUsername())) {
            log.error("Fraudulent user! {}", persistedUser);
        }

        persistedUser.setEmail(user.getEmail());
        //persistedUser.setUsername(user.getUsername());
        persistedUser.setFirstName(user.getFirstName());
        persistedUser.setLastName(user.getLastName());
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
