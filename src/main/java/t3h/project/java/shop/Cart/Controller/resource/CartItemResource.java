//package t3h.project.java.shop.Cart.Controller.resource;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import t3h.project.java.shop.Cart.Model.CartItem;
//import t3h.project.java.shop.Cart.Service.CartItemService;
//import t3h.project.java.shop.CommonData.model.ResponseHandler;
//import t3h.project.java.shop.User.Model.User;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/cart")
//public class CartItemResource {
//    private final CartItemService service;
//
//    public CartItemResource(CartItemService service) {
//        this.service = service;
//    }
//    @GetMapping()
//    public ResponseEntity<Object> addToCart(@PathVariable Long productId, @PathVariable Integer quantity, @PathVariable String name){
//        CartItem cartItem = service.addItems(productId,quantity,name);
//        return ResponseHandler.getResponse(cartItem, HttpStatus.OK);
//    }
//    @PostMapping()
//    public ResponseEntity<List<CartItem>> getAll(){
//        User user = new User();
//        return ResponseEntity.ok(service.listCartItems(user));
//    }
//}
