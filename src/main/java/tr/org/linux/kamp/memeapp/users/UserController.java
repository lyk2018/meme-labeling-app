package tr.org.linux.kamp.memeapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.org.linux.kamp.memeapp.memes.Meme;
import tr.org.linux.kamp.memeapp.memes.MemeService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MemeService memeService;

    @GetMapping
    String findAll(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        Iterable<Meme> memes = memeService.findAllByUserId(id);
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
    String update(@PathVariable Long id, User user) {
        User persistedUser = userService.findById(id);

        persistedUser.setEmail(user.getEmail());
        persistedUser.setUsername(user.getUsername());
        persistedUser.setFirstName(user.getFirstName());
        persistedUser.setLastName(user.getLastName());

        userService.save(persistedUser);
        return "redirect:/users/{id}";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
