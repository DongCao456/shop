package t3h.project.java.shop.Brand.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.CommonData.model.ResponseHandler;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CreateBrandDto createBrandDto){
        Brand brand = brandService.createBrand(createBrandDto);
        return ResponseHandler.getResponse(brand, HttpStatus.OK);
    }
}
