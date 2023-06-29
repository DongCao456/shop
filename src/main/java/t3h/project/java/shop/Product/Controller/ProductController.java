package t3h.project.java.shop.Product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CreateProductDto dto){
        Product product=productService.saveNew(dto);
        return ResponseHandler.getResponse(product, HttpStatus.OK);
    }
}
