package t3h.project.java.shop.Order.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Service.OrderService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private UserService userService;
    private OrderService orderService;

    @GetMapping("/checkout/{name}")
    public ResponseEntity<Object> checkout(@PathVariable String name){
        Optional<User> user=userService.findByUsername(name);
        if(!user.isPresent()) {
            return ResponseHandler.getResponse("User is invalid", HttpStatus.BAD_REQUEST);
        }
        Order order=orderService.checkOut(name);
        return ResponseHandler.getResponse(order, HttpStatus.OK);
    }
}
