package t3h.project.java.shop.Product.Controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.Product.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Object> findList(){
        return ResponseHandler.getResponse(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CreateProductDto dto){
        Product product=productService.saveNew(dto);
        return ResponseHandler.getResponse(product, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Page<CreateProductDto>>> getAll(@RequestBody ProductFilterRequest filterRequest,
                                                                       @RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                                       @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        return ResponseEntity.ok(productService.getAll(filterRequest, page, size));
    }


}
