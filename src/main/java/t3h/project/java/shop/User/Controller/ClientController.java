package t3h.project.java.shop.User.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.RoleService;
import t3h.project.java.shop.User.Service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping()
public class ClientController {
    private final ProductService productService;
    private final UserService userService;
    private final RoleService roleService;

    public ClientController(ProductService productService, UserService userService, RoleService roleService) {
        this.productService = productService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{username}/phone")
    public String phone(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/phone";
    }
    @GetMapping("/{username}/laptop")
    public String laptop(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/laptop";
    }
    @GetMapping("/{username}/tablet")
    public String tablet(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/tablet";
    }
    @GetMapping("/{username}/watch")
    public String watch(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/watch";
    }
    @GetMapping("/{username}/accessory")
    public String accessory(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/accessory";
    }
    @GetMapping("/{username}/cart")
    public String cart(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/cart";
    }
    @GetMapping("/{username}/order")
    public String order(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/order";
    }
    @GetMapping("/{username}/details/{id}")
    public String getDetails(@PathVariable("id") Long id,@PathVariable("username")String username, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        System.out.println(productDto);
        model.addAttribute("product", productDto);
        model.addAttribute("id", id);
        model.addAttribute("username", username);
        return "client/product-detail";
    }
    @GetMapping("/{username}/order-detail/{id}")
    public String getOrderDetails(@PathVariable("id") Long id, @PathVariable(name = "username")String username, Model model){
        model.addAttribute("id", id);
        model.addAttribute("username", username);
        return "client/order-detail";
    }
    @GetMapping("/register")
    public String getFormRegister(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "client/register";
    }
    @PostMapping("/save")
    public String saveInfo(@ModelAttribute(name = "user")User user){
        if(userService.findByUsername(user.getUsername()) == null){
            Optional<Role> role = roleService.findById(Long.valueOf(3));
            user.getRoles().add(role.get());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String bcryptPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(bcryptPassword);
            userService.save(user);
            userService.saveUserRoles(user.getId(), role.get().getId());
        }
        return "redirect:/login";
    }

}
