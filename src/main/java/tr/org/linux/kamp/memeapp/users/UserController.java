package tr.org.linux.kamp.memeapp.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.org.linux.kamp.memeapp.memes.MemeService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final MemeService memeService;

    @GetMapping
    String findAll(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/show";
    }

    @GetMapping("/new")
    String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/new";
    }

    @PostMapping("")
    String create(User user) {
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
    String update(User user) {
        userService.update(user);
        return "redirect:/users/{id}";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
