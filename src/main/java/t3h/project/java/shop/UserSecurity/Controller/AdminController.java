package t3h.project.java.shop.UserSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @GetMapping("/index")
    public String indexPage(Principal principal, Model model){
//        if(principal == null){
//            return "redirect:/admin/login";
//        }

//        Optional<User> user=service.findByUsername(principal.getName());
//        model.addAttribute("user",user.get());

        return "Admin";
    }

    @GetMapping("/home")
    public String indexPage2(){
        return "Index2";
    }
}
