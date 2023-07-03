package t3h.project.java.shop.Order.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.Order.Model.Order;

public interface OrderService extends GenericService<Order,Long> {

    Order checkOut(String name);
}
