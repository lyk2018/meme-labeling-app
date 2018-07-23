package tr.org.linux.kamp.memeapp.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.org.linux.kamp.memeapp.users.User;
import tr.org.linux.kamp.memeapp.users.UserService;

@Controller
@RequestMapping("/users/{userId}/memes")
public class MemeController {

    @Autowired
    private MemeService memeService;

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    String newMeme(Model model, @PathVariable Long userId) {
        Meme meme = new Meme();
        model.addAttribute("userId", userId);
        model.addAttribute("meme", meme);
        return "memes/new";
    }

    @PostMapping
    String create(Meme meme, @PathVariable Long userId) {

        User user = userService.findById(userId);
        meme.setOwner(user);

        memeService.save(meme);
        return "redirect:/users/{userId}/";
    }

}
