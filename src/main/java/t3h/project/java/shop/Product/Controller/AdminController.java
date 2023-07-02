package t3h.project.java.shop.Product.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.Product.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/phone")
    public String phone(){
        return "phone";
    }
    @GetMapping("/laptop")
    public String laptop(){
        return "laptop";
    }
    @GetMapping("/tablet")
    public String tablet(){
        return "tablet";
    }
    @GetMapping("/watch")
    public String index(){
        return "watch";
    }
    @GetMapping("/accessory")
    public String accessory(){
        return "accessory";
    }
}
