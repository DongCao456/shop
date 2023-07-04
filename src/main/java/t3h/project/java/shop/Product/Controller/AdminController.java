package t3h.project.java.shop.Product.Controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
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
//Phone
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
    @PostMapping("/savePhone")
    public String savePhone(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.insertProduct(productDto);
        return "redirect:/admin/phone";
    }
    @PostMapping("/updatePhone")
    public String updatePhone(@ModelAttribute(name = "product")CreateProductDto productDto){
        System.out.println(productDto.getId());
        productService.updateProduct(productDto);
        return "redirect:/admin/phone";
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
    @GetMapping("/deletePhone/{id}")
    public String deletePhone(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/phone";
    }
//    Laptop
@GetMapping("/form-create-laptop")
public String getFormCreateLaptop(Model model){
    CreateProductDto productDto = new CreateProductDto();
    model.addAttribute("product", productDto);

    List<CreateBrandDto> brandDtos = brandService.getAll();
    model.addAttribute("brands", brandDtos);

    List<CreateCateDto> cateDtos = cateService.getAll();
    model.addAttribute("cates", cateDtos);
    return "admin/laptop-create";
}
    @PostMapping("/saveLaptop")
    public String saveLaptop(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.insertProduct(productDto);
        return "redirect:/admin/laptop";
    }
    @PostMapping("/updateLaptop")
    public String updateLaptop(@ModelAttribute(name = "product")CreateProductDto productDto){
        System.out.println(productDto.getId());
        productService.updateProduct(productDto);
        return "redirect:/admin/laptop";
    }
    @GetMapping("/form-update-laptop/{id}")
    public String getFormUpdateLaptop(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/laptop-update";
    }
    @GetMapping("/deleteLaptop/{id}")
    public String deleteLaptop(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/laptop";
    }
//    Tablet
@GetMapping("/form-create-tablet")
public String getFormCreateTablet(Model model){
    CreateProductDto productDto = new CreateProductDto();
    model.addAttribute("product", productDto);

    List<CreateBrandDto> brandDtos = brandService.getAll();
    model.addAttribute("brands", brandDtos);

    List<CreateCateDto> cateDtos = cateService.getAll();
    model.addAttribute("cates", cateDtos);
    return "admin/tablet-create";
}
    @PostMapping("/saveTablet")
    public String saveTablet(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.insertProduct(productDto);
        return "redirect:/admin/tablet";
    }
    @PostMapping("/updateTablet")
    public String updateTablet(@ModelAttribute(name = "product")CreateProductDto productDto){
        System.out.println(productDto.getId());
        productService.updateProduct(productDto);
        return "redirect:/admin/tablet";
    }
    @GetMapping("/form-update-tablet/{id}")
    public String getFormUpdateTablet(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/tablet-update";
    }
    @GetMapping("/deleteTablet/{id}")
    public String deleteTablet(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/tablet";
    }
//    Watch
@GetMapping("/form-create-watch")
public String getFormCreateWatch(Model model){
    CreateProductDto productDto = new CreateProductDto();
    model.addAttribute("product", productDto);

    List<CreateBrandDto> brandDtos = brandService.getAll();
    model.addAttribute("brands", brandDtos);

    List<CreateCateDto> cateDtos = cateService.getAll();
    model.addAttribute("cates", cateDtos);
    return "admin/watch-create";
}
    @PostMapping("/saveWatch")
    public String saveWatch(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.insertProduct(productDto);
        return "redirect:/admin/watch";
    }
    @PostMapping("/updateWatch")
    public String updateWatch(@ModelAttribute(name = "product")CreateProductDto productDto){
        System.out.println(productDto.getId());
        productService.updateProduct(productDto);
        return "redirect:/admin/watch";
    }
    @GetMapping("/form-update-watch/{id}")
    public String getFormUpdateWatch(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/watch-update";
    }
    @GetMapping("/deleteWatch/{id}")
    public String deleteWatch(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/watch";
    }
//    Accessory
@GetMapping("/form-create-accessory")
public String getFormCreateAccessory(Model model){
    CreateProductDto productDto = new CreateProductDto();
    model.addAttribute("product", productDto);

    List<CreateBrandDto> brandDtos = brandService.getAll();
    model.addAttribute("brands", brandDtos);

    List<CreateCateDto> cateDtos = cateService.getAll();
    model.addAttribute("cates", cateDtos);
    return "admin/accessory-create";
}
    @PostMapping("/saveAccessory")
    public String saveAccessory(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.insertProduct(productDto);
        return "redirect:/admin/accessory";
    }
    @PostMapping("/updateAccessory")
    public String updateAccessory(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.updateProduct(productDto);
        return "redirect:/admin/accessory";
    }
    @GetMapping("/form-update-accessory/{id}")
    public String getFormUpdateAccessory(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/accessory-update";
    }
    @GetMapping("/deleteAccessory/{id}")
    public String deleteAccessory(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/accessory";
    }
//    Brand
@GetMapping("/form-create-brand")
public String getFormCreateBrand(Model model){
    CreateBrandDto brandDto = new CreateBrandDto();
    model.addAttribute("brand", brandDto);
    return "admin/brand-create";
}
    @PostMapping("/saveBrand")
    public String saveBrand(@ModelAttribute(name = "brand")CreateBrandDto brandDto){
        brandService.update(brandDto);
        return "redirect:/admin/brand";
    }
    @PostMapping("/updateBrand")
    public String updateBrand(@ModelAttribute(name = "brand")CreateBrandDto brandDto){
        brandService.update(brandDto);
        return "redirect:/admin/brand";
    }
    @GetMapping("/form-update-brand/{id}")
    public String getFormUpdateBrand(@PathVariable("id") Long id, Model model){
        CreateBrandDto brandDto = brandService.findBrandDtoById(id);
        System.out.println(brandDto.getUrl());
        model.addAttribute("brand", brandDto);
        return "admin/brand-update";
    }
    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        brandService.deleteById(id);
        return "redirect:/admin/brand";
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
    @GetMapping("/brand")
    public String brand(){
        return "admin/brand";
    }
    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

}
