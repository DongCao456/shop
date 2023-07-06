package t3h.project.java.shop.Product.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/logined")
public class UserLoginController {
    @GetMapping("/index")
    public String index(){
        return "logined/index";
    }
    @GetMapping("/phone")
    public String phone(){
        return "logined/phone";
    }
    @GetMapping("/laptop")
    public String laptop(){
        return "logined/laptop";
    }
    @GetMapping("/tablet")
    public String tablet(){
        return "logined/tablet";
    }
    @GetMapping("/watch")
    public String watch(){
        return "logined/watch";
    }
    @GetMapping("/accessory")
    public String accessory(){
        return "logined/accessory";
    }
}
