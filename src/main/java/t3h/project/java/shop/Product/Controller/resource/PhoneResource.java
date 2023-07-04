package t3h.project.java.shop.Product.Controller.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.Product.Service.ProductService;

@RestController
@RequestMapping("/phone")
public class PhoneResource {
    private final ProductService productService;
    private final BrandService brandService;
    private final CateService cateService;

    public PhoneResource(ProductService productService, BrandService brandService, CateService cateService) {
        this.productService = productService;
        this.brandService = brandService;
        this.cateService = cateService;
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Page<CreateProductDto>>> getAll(@RequestBody ProductFilterRequest filterRequest,
                                                                 @RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                                 @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        filterRequest.setCategoryId(1);
        return ResponseEntity.ok(productService.getAllByCategory(filterRequest, page, size));
    }

}
