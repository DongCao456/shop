package t3h.project.java.shop.User.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.Category.Dto.CreateCateDto;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.RoleService;
import t3h.project.java.shop.User.Service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CateService cateService;
    private final UserService userService;
    private final RoleService roleService;
    private final CartItemService cartItemService;

    public AdminController(ProductService productService, BrandService brandService, CateService cateService, UserService service, RoleService roleService, CartItemService cartItemService) {
        this.productService = productService;
        this.brandService = brandService;
        this.cateService = cateService;
        this.userService = service;
        this.roleService = roleService;
        this.cartItemService = cartItemService;
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
        System.out.println(brandDto);
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
        model.addAttribute("brand", brandDto);
        return "admin/brand-update";
    }
    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        brandService.deleteById(id);
        return "redirect:/admin/brand";
    }
    //Account
    @GetMapping("/form-create-account")
    public String getFormCreateAccount(Model model){
        CreateUserDto userDto = new CreateUserDto();
        model.addAttribute("user", userDto);

        List<CreateRoleDto> roleDtos = roleService.getAll();
        model.addAttribute("roles", roleDtos);
        return "admin/account-create";
    }
    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute(name = "user")CreateUserDto userDto){
        userService.insertUser(userDto);
        return "redirect:/admin/account";
    }
    @PostMapping("/updateAccount")
    public String updateAccount(@ModelAttribute(name = "user")CreateUserDto userDto){
        userService.updateUser(userDto);
        return "redirect:/admin/account";
    }
    @GetMapping("/form-update-account/{id}")
    public String getFormUpdateAccount(@PathVariable("id") Long id, Model model){
        CreateUserDto userDto = userService.findUserById(id);
        model.addAttribute("user", userDto);

        List<CreateRoleDto> roleDtos = roleService.getAll();
        model.addAttribute("roles", roleDtos);
        return "admin/account-update";
    }
    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/account";
    }
    //Product
    @GetMapping("/form-create-product")
    public String getFormCreateProduct(Model model){
        CreateProductDto productDto = new CreateProductDto();
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);
        return "admin/product-create";
    }
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.insertProduct(productDto);
        return "redirect:/admin/product";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute(name = "product")CreateProductDto productDto){
        productService.updateProduct(productDto);
        return "redirect:/admin/product";
    }
    @GetMapping("/form-update-product/{id}")
    public String getFormUpdateProduct(@PathVariable("id") Long id, Model model){
        CreateProductDto productDto = productService.findProductById(id);
        model.addAttribute("product", productDto);

        List<CreateBrandDto> brandDtos = brandService.getAll();
        model.addAttribute("brands", brandDtos);

        List<CreateCateDto> cateDtos = cateService.getAll();
        model.addAttribute("cates", cateDtos);

        return "admin/product-update";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/product";
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
    @GetMapping("/account")
    public String account(){
        return "admin/account";
    }
    @GetMapping("/product")
    public String product(){
        return "admin/product";
    }

    @GetMapping("/index")
    public String indexPage(Principal principal, Model model){
        if(principal == null){
            return "redirect:/login";
        }

        User user= userService.findByUsername(principal.getName());
        model.addAttribute("user",user);

        return "admin/index";
    }
    @PostMapping("/importProduct")
    public String importFile(@RequestParam("file") MultipartFile file){
        productService.importProductExcel(file);
        return "redirect:/admin/product";
    }

    @GetMapping("/exportProduct")
    public String exportFile(){
        productService.export();
        return "redirect:/admin/product";
    }
    @PostMapping("/importBrand")
    public String importBrand(@RequestParam("file") MultipartFile file){
        brandService.importBrandExcel(file);
        return "redirect:/admin/brand";
    }

    @PostMapping("/importAccountUser")
    public String importAccountUser(@RequestParam("file") MultipartFile file){
        userService.importAccountUser(file);
        return "redirect:/admin/account";
    }
    @GetMapping("/{username}/updateCart/{id}")
    public String getFormUpdateCartItem(@PathVariable(name = "username")String username, @PathVariable("id")Long id, Model model){
        model.addAttribute("username", username);
        model.addAttribute("id", id);
        Optional<CartItem> cartItemOptional = cartItemService.findById(id);
        CartItem cartItem = cartItemOptional.get();

        return "client/update-cart";
    }

}
