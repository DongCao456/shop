package t3h.project.java.shop.Product.Controller.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.Product.Service.ProductService;

@RestController
@RequestMapping("/accessory")
public class AccessoryController {
    private final ProductService service;
    public AccessoryController(ProductService service) {
        this.service = service;
    }
    @PostMapping()
    public ResponseEntity<BaseResponse<Page<CreateProductDto>>> getAll(@RequestBody ProductFilterRequest filterRequest,
                                                                 @RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                                 @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        filterRequest.setCategoryId(5);
        return ResponseEntity.ok(service.getAllByCategory(filterRequest, page, size));
    }

}
