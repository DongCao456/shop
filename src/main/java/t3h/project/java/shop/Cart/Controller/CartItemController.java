package t3h.project.java.shop.Cart.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.CommonData.model.ResponseHandler;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartItemController {
    private CartItemService cartItemService;

    @GetMapping("/add-to-cart/{productId}/{quantity}/{name}")
    public ResponseEntity<Object> addToCard(@PathVariable  Long productId,@PathVariable Integer quantity,@PathVariable String name){
        CartItem cartItem=cartItemService.addItems(productId,quantity,name);
        return ResponseHandler.getResponse(cartItem, HttpStatus.OK);
    }
}
