package t3h.project.java.shop.Product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

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

    @PostMapping("/import")
    public ResponseEntity<Object> importFile(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(productService.importProductExcel(file));
    }
}
