package t3h.project.java.shop.Order.Service;

import org.springframework.data.domain.Page;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Order.Dto.CreateOrderDto;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

public interface OrderService extends GenericService<Order,Long> {

    Order checkOut(String name);
    BaseResponse<Page<CreateOrderDto>> getAllByUsername(OrderFilterRequest filterRequest, int page, int size);
}
