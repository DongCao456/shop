package t3h.project.java.shop.Cart.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.CommonData.model.IAuthenticationFacade;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/client/cart")
@AllArgsConstructor
public class CartItemController {
    private CartItemService cartItemService;
    private UserService userService;

    @GetMapping("/add-to-cart/{productId}/{quantity}/{name}")
    public ResponseEntity<Object> addToCard(@PathVariable  Long productId, @PathVariable Integer quantity,@PathVariable String name){
        Optional<User> user=userService.findByUsername(name);
        if (!user.isPresent()){
            return ResponseHandler.getResponse("UserName is invalid",HttpStatus.BAD_REQUEST);
        }
        CartItem cartItem=cartItemService.addItems(productId,quantity,name);
        return ResponseHandler.getResponse(cartItem, HttpStatus.OK);
    }

    @GetMapping("/update-cart/{productId}/{quantity}/{name}")
    public ResponseEntity<Object> updateCart(@PathVariable Long productId, @PathVariable Integer quantity,@PathVariable String name){

        CartItem cartItem=cartItemService.updateItems(productId,quantity,name);
        return ResponseHandler.getResponse(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete-cart/{id}")
    public  ResponseEntity<Object> deleteCart(@PathVariable Long id){
        cartItemService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
