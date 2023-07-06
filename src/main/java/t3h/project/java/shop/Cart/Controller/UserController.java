package t3h.project.java.shop.Cart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logined")
public class UserController {
    @GetMapping("/cart")
    public String cart(){
        return "logined/cart";
    }

}
