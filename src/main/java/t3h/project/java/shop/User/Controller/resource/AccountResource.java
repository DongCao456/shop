package t3h.project.java.shop.User.Controller.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.Request.UserFilterRequest;
import t3h.project.java.shop.User.Service.UserService;

@RestController
@RequestMapping("/account")
public class AccountResource {
    private final UserService service;

    public AccountResource(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Page<CreateUserDto>>> getAll(@RequestBody UserFilterRequest filterRequest,
                                                                    @RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                                    @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        return ResponseEntity.ok(service.getAllByFilter(filterRequest, page, size));
    }

}
