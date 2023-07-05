package t3h.project.java.shop.Order.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Service.CustomerService;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Model.OrderItems;
import t3h.project.java.shop.Order.Repository.OrderItemsRepository;
import t3h.project.java.shop.Order.Repository.OrderRepository;
import t3h.project.java.shop.Order.Service.OrderItemsService;
import t3h.project.java.shop.Order.Service.OrderService;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;
import t3h.project.java.shop.Utils.DefaultString;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends GenericServiceImpl<Order,Long> implements OrderService {

    private UserService userService;
    private OrderItemsRepository orderItemsRepository;
    private OrderRepository orderRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    private CustomerService customerService;


    @Override
    public Order checkOut(String name) {
        //Optional<User> user=userService.findByUsername(name);
        Optional<Customer> customer=customerService.findByUsername(name);
        Order order=new Order();
        order.setDeliveryDate(LocalDate.now());
        //order.setUser(user.get());
        order.setCustomer(customer.get());
        order.setStatus(DefaultString.PENDING);
        Set<OrderItems> orderItemsList=new HashSet<>();
        Float total= (float) 0;
        for (CartItem cartItem : customer.get().getCarts()){
            OrderItems orderItems=new OrderItems();
            orderItems.setProduct(cartItem.getProduct());
            orderItems.setPrice(cartItem.getPrice());
            orderItems.setQuantity(cartItem.getQuantity());

            //orderItems.setOrder(order);

            orderItemsRepository.save(orderItems);
            order.addItems(orderItems);
            cartItemService.deleteById(cartItem.getId());
            Optional<Product> product=productService.findById(cartItem.getProduct().getId());
            product.get().setQuantity(product.get().getQuantity()-cartItem.getQuantity());
            productService.save(product.get());
            total+=cartItem.getPrice();

        }

        order.setTotalCost(total);

        return orderRepository.save(order);
    }
}
