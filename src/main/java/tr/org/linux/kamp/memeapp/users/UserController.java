package tr.org.linux.kamp.memeapp.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tr.org.linux.kamp.memeapp.memes.Meme;
import tr.org.linux.kamp.memeapp.memes.MemeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final MemeService memeService;

    private final UserValidator userValidator;

    @InitBinder("user")
    void addCustomUserValidator(WebDataBinder binder) {
        binder.addValidators(this.userValidator);
    }

    @GetMapping
    String findAll(Model model, Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        model.addAttribute("users", users);

        return "users/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model, Pageable pageable) {
        User user = userService.findById(id);
        Page<Meme> memes = memeService.findAll(id, pageable);
        model.addAttribute("user", user);
        model.addAttribute("memes", memes);
        return "users/show";
    }

    @GetMapping("/new")
    String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/new";
    }

    @PostMapping("")
    String create(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "users/new";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    String edit(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PutMapping("/{id}")
    String update(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        userService.update(user);
        return "redirect:/users/{id}";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    String handleUniqueConstraintException(ConstraintViolationException e, Model model) {

        log.error(e.getMessage(), e);

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("message", "Username already exists.");
        return "users/new";
    }

}
