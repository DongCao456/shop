package t3h.project.java.shop.UserSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginAdmin")
public class LoginController {



    @GetMapping()
    public String loginForm(){
        return "Login";
    }



}
