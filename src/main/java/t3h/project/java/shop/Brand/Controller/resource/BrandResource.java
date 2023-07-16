package t3h.project.java.shop.Brand.Controller.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Request.BrandFilterRequest;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

@RestController
@RequestMapping("/brand")
public class BrandResource {
    private final BrandService service;

    public BrandResource(BrandService service) {
        this.service = service;
    }
    @PostMapping()
    public ResponseEntity<BaseResponse<Page<CreateBrandDto>>> getAll(@RequestBody BrandFilterRequest filterRequest,
                                                                     @RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                                     @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        return ResponseEntity.ok(service.getAll(filterRequest, page, size));
    }
}
