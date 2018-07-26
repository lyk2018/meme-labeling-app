package tr.org.linux.kamp.memeapp.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        final User user = (User) target;

        // If id is not null, that means this is an edit operation and username should not be checked.
        if (user.getId() == null && userService.existsByUsername(user.getUsername())) {
            errors.rejectValue("username", "user.usernameUniquenessError", "already in use");
        }

        // We don't update username in service layer so we don't need this
        //if(user.getId() != null && !getCurrentUsername(user.getId()).equals(user.getUsername())) {
        //    errors.rejectValue("username", "user.usernameChangeError", "cannot change username");
        //}

    }

    private String getCurrentUsername(Long id) {
        return userService.findById(id).getUsername();
    }

}
