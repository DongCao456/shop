package t3h.project.java.shop.Order.Service.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Order.Dto.CreateOrderDto;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Model.OrderItems;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Order.Repository.OrderItemsRepository;
import t3h.project.java.shop.Order.Repository.OrderRepository;
import t3h.project.java.shop.Order.Service.OrderService;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;
import t3h.project.java.shop.Utils.DefaultString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends GenericServiceImpl<Order,Long> implements OrderService {

    private final UserService userService;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final ModelMapper modelMapper;


    @Override
    public Order checkOut(String name) {
        User user = userService.findByUsername(name);
        Order order=new Order();
        order.setDeliveryDate(LocalDate.now().plusDays(3));
        order.setUser(user);
        order.setStatus(DefaultString.PENDING);
        Set<OrderItems> orderItemsList=new HashSet<>();
        Float total= (float) 30000;
        for (CartItem cartItem : user.getCarts()){
            OrderItems orderItems=new OrderItems();
            orderItems.setProduct(cartItem.getProduct());
            orderItems.setPrice(cartItem.getPrice());
            orderItems.setQuantity(cartItem.getQuantity());


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

    @Override
    public BaseResponse<Page<CreateOrderDto>> getAllByUsername(OrderFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderEntities = orderRepository.findAllOrderByUsername(filterRequest, pageable);
        System.out.println(orderEntities.getTotalElements());

        List<CreateOrderDto> orderDtos = orderEntities.getContent().stream()
                .map(orderEntity -> {
                    CreateOrderDto orderDto = modelMapper.map(orderEntity, CreateOrderDto.class);
                    System.out.println(orderDto.getTotalCost());
                    orderDto.setUsername(orderEntity.getUser().getUsername());
                    return orderDto;
                })
                .collect(Collectors.toList());

        Page<CreateOrderDto> pageData = new PageImpl<>(orderDtos, pageable, orderEntities.getTotalElements());
        BaseResponse<Page<CreateOrderDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }
}
