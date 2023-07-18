package t3h.project.java.shop.Order.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Order.Dto.CreateOrderDto;
import t3h.project.java.shop.Order.Dto.CreateOrderItemDto;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Model.OrderItems;
import t3h.project.java.shop.Order.Model.Request.OrderItemFilterRequest;
import t3h.project.java.shop.Order.Repository.OrderItemsRepository;
import t3h.project.java.shop.Order.Service.OrderItemsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsServiceImpl extends GenericServiceImpl<OrderItems,Long> implements OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;
    private final ModelMapper modelMapper;

    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository, ModelMapper modelMapper) {
        this.orderItemsRepository = orderItemsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BaseResponse<Page<CreateOrderItemDto>> getAllByOrderId(OrderItemFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderItems> orderItemsEntities = orderItemsRepository.findAllOrderItemsByOrderId(filterRequest, pageable);
        System.out.println(orderItemsEntities.getTotalElements());

        List<CreateOrderItemDto> orderItemDtos = orderItemsEntities.getContent().stream()
                .map(orderItemsEntity -> {
                    CreateOrderItemDto orderItemDto = modelMapper.map(orderItemsEntity, CreateOrderItemDto.class);
                    orderItemDto.setImage(orderItemsEntity.getProduct().getImage());
                    orderItemDto.setPrice(orderItemsEntity.getPrice());
                    orderItemDto.setPriceOfOne(orderItemsEntity.getProduct().getPrice());
                    orderItemDto.setProductName(orderItemsEntity.getProduct().getName());
                    orderItemDto.setProductId(orderItemsEntity.getProduct().getId());
                    return orderItemDto;
                })
                .collect(Collectors.toList());

        Page<CreateOrderItemDto> pageData = new PageImpl<>(orderItemDtos, pageable, orderItemsEntities.getTotalElements());
        BaseResponse<Page<CreateOrderItemDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }
}
