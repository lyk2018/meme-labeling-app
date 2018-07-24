package tr.org.linux.kamp.memeapp.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Validation;

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

        User user = (User) target;

        if (userService.existsByUsername(user.getUsername())) {
            errors.rejectValue("username", "user.usernameUniquenessError", "already in use");
        }

    }
}
