package t3h.project.java.shop.CustomerSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.Product.Service.ProductService;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ProductService service;

    public ClientController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/phone")
    public String phone(){
        return "client/phone";
    }
    @GetMapping("/laptop")
    public String laptop(){
        return "client/laptop";
    }
    @GetMapping("/tablet")
    public String tablet(){
        return "client/tablet";
    }
    @GetMapping("/watch")
    public String watch(){
        return "client/watch";
    }
    @GetMapping("/accessory")
    public String accessory(){
        return "client/accessory";
    }
    @GetMapping("/cart")
    public String cart(){
        return "client/cart";
    }
    @GetMapping("/details/{id}")
    public String getFormUpdateBrand(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = service.findProductById(id);
        System.out.println(productDto);
        model.addAttribute("product", productDto);
        return "client/product-detail";
    }
}
