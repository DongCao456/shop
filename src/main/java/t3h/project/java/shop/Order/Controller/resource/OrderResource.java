package t3h.project.java.shop.Order.Controller.resource;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.Cart.Dto.CreateCartItemDto;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Order.Dto.CreateOrderDto;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Order.Service.OrderItemsService;
import t3h.project.java.shop.Order.Service.OrderService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

@RestController
@RequestMapping()
@AllArgsConstructor
public class OrderResource {
    private final OrderService orderService;
    private final OrderItemsService orderItemsService;
    private final UserService userService;


    @PostMapping("/{username}/order")
    public ResponseEntity<BaseResponse<Page<CreateOrderDto>>> getAll(
            @PathVariable(name = "username")String username,
            @RequestBody OrderFilterRequest filterRequest,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        User user = userService.findByUsername(username);
        System.out.println(username);
        filterRequest.setUserId(user.getId());
        filterRequest.setUsername(user.getUsername());
        return ResponseEntity.ok(orderService.getAllByUsername(filterRequest, page, size));
    }
}
