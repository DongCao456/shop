package t3h.project.java.shop.Order.Controller.resource;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Order.Dto.CreateOrderDto;
import t3h.project.java.shop.Order.Dto.CreateOrderItemDto;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Order.Model.Request.OrderItemFilterRequest;
import t3h.project.java.shop.Order.Service.OrderItemsService;
import t3h.project.java.shop.Order.Service.OrderService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

@RestController
@RequestMapping()
@AllArgsConstructor
public class OrderDetailResource {
    private final OrderService orderService;
    private final OrderItemsService orderItemsService;
    private final UserService userService;


    @PostMapping("/{username}/order-detail/{id}")
    public ResponseEntity<BaseResponse<Page<CreateOrderItemDto>>> getAll(
            @PathVariable(name = "username")String username,
            @PathVariable(name = "id")Long orderId,
            @RequestBody OrderItemFilterRequest filterRequest,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        filterRequest.setOrderId(orderId);
        return ResponseEntity.ok(orderItemsService.getAllByOrderId(filterRequest, page, size));
    }
}
