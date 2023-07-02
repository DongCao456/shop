package t3h.project.java.shop.Product.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Dto.CreateCateDto;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final BrandService brandService;
    private final CateService cateService;

    public AdminController(ProductService productService, BrandService brandService, CateService cateService) {
        this.productService = productService;
        this.brandService = brandService;
        this.cateService = cateService;
    }

    @GetMapping("/form-create-phone")
    public String getFormCreatePhone(Model model){
        CreateProductDto productDto = new CreateProductDto();
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/phone-create";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute(name = "product") CreateProductDto productDto){
        productService.
        return "redirect:/user";
    }
    @GetMapping("/form-update-phone/{id}")
    public String getFormUpdatePhone(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/phone-update";
    }
    @GetMapping("/phone")
    public String phone(){
        return "admin/phone";
    }
    @GetMapping("/laptop")
    public String laptop(){
        return "admin/laptop";
    }
    @GetMapping("/tablet")
    public String tablet(){
        return "admin/tablet";
    }
    @GetMapping("/watch")
    public String watch(){
        return "admin/watch";
    }
    @GetMapping("/accessory")
    public String accessory(){
        return "admin/accessory";
    }
    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }
}
