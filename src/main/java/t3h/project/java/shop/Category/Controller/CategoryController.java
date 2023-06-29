package t3h.project.java.shop.Category.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Category.Dto.CreateCateDto;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.model.ResponseHandler;

@RequestMapping("/api/cate")
@RestController
public class CategoryController {

    @Autowired
    private CateService cateService;

    @GetMapping()
    public ResponseEntity<Object> findList(){
        return ResponseHandler.getResponse(cateService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveCate(@RequestBody CreateCateDto createCateDto){
        Category category=cateService.createCateDto(createCateDto);
        return ResponseHandler.getResponse(category,HttpStatus.OK);
    }
}
