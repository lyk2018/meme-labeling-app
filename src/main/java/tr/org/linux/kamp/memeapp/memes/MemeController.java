package tr.org.linux.kamp.memeapp.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    String show(Model model, @PathVariable("id") Long id) {
        final Meme meme = memeService.findById(id);
        model.addAttribute("meme", meme);
        return "memes/show";
    }

    @PostMapping
    String create(Meme meme, @PathVariable Long userId) {

        User user = userService.findById(userId);
        meme.setOwner(user);

        memeService.save(meme);
        return "redirect:/users/{userId}/";
    }

    @GetMapping("/{id}/edit")
    String editForm(@PathVariable("userId") Long userId, @PathVariable("id") Long memeId, Model model) {
        final Meme meme = memeService.findById(memeId);

        model.addAttribute("meme", meme);
        model.addAttribute("userId", userId);
        return "memes/edit";
    }

    @PutMapping("/{id}")
    String edit(@PathVariable("id") Long id, Meme meme) {

        memeService.update(meme);

        //return "redirect:/users/" + userId + "/memes/" + memeId + "/edit";
        return "redirect:edit";
    }

}
