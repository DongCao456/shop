package t3h.project.java.shop.UserSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/index")
public class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String indexPage(Principal principal, Model model){
        if(principal == null){
            return "redirect:/login";
        }

        User user= userService.findByUsername(principal.getName());
        model.addAttribute("user",user);

        return "client/index";
    }
}
