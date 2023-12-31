package t3h.project.java.shop.Cart.Controller.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Brand.Model.Request.BrandFilterRequest;
import t3h.project.java.shop.Cart.Dto.CreateCartItemDto;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Model.Request.CartItemFilterRequest;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import javax.security.auth.x500.X500Principal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartResource {
    private final CartItemService cartItemService;
    private final UserService userService;

    public CartResource(CartItemService cartItemService, UserService userService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<BaseResponse<Page<CreateCartItemDto>>> getAll(
            @PathVariable(name = "username")String username,
            @RequestBody CartItemFilterRequest filterRequest,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        User user = userService.findByUsername(username);
        filterRequest.setUserId(user.getId());
        filterRequest.setUsername(user.getUsername());
        return ResponseEntity.ok(cartItemService.getAll(filterRequest, page, size));
    }
}
