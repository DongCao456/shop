package t3h.project.java.shop.Order.Service;

import org.springframework.data.domain.Page;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Order.Dto.CreateOrderDto;
import t3h.project.java.shop.Order.Dto.CreateOrderItemDto;
import t3h.project.java.shop.Order.Model.OrderItems;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Order.Model.Request.OrderItemFilterRequest;

public interface OrderItemsService extends GenericService<OrderItems,Long> {
    BaseResponse<Page<CreateOrderItemDto>> getAllByOrderId(OrderItemFilterRequest filterRequest, int page, int size);
}
