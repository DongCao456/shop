package t3h.project.java.shop.CustomerSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class LoginClientController {

    @GetMapping("/login")
    public String loginForm11() {
        return "ClientLogin";
    }
}
