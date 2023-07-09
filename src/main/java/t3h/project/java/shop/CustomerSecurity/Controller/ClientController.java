package t3h.project.java.shop.CustomerSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Service.CustomerService;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ProductService productService;

    private final CartItemService cartItemService;
    private final CustomerService customerService;
    public ClientController(ProductService productService, CartItemService cartItemService, CustomerService customerService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.customerService = customerService;
    }

    @GetMapping("/phone/{username}")
    public String phone(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/phone";
    }
    @GetMapping("/laptop/{username}")
    public String laptop(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/laptop";
    }
    @GetMapping("/tablet/{username}")
    public String tablet(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/tablet";
    }
    @GetMapping("/watch/{username}")
    public String watch(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/watch";
    }
    @GetMapping("/accessory/{username}")
    public String accessory(@PathVariable(name = "username")String username, Model model){
        model.addAttribute("username", username);
        return "client/accessory";
    }
    @GetMapping("/cart/{username}")
    public String cart(@PathVariable(name = "username")String username){
        Optional<Customer> customer=customerService.findByUsername(username);
//        System.out.println(customer.get().getAddress());
        return "client/cart";
    }
    @GetMapping("/details/{id}")
    public String getFormUpdateBrand(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        System.out.println(productDto);
        model.addAttribute("product", productDto);
        return "client/product-detail";
    }
    @PostMapping("/addToCart/{username}/{id}")
    public String saveToCart(@PathVariable(name = "id")Long id, @PathVariable(name = "username")String username){
        if(cartItemService.addItems(id, 1, username) != null){
            System.out.println("Success");
        }
        else{
            System.out.println("Error");
        }
        return "redirect:/client/cart/" + username;
    }
}
