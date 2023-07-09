package t3h.project.java.shop.Cart.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Cart.Dto.CreateCartItemDto;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Model.Request.CartItemFilterRequest;
import t3h.project.java.shop.Cart.Repository.CartItemRepository;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Service.CustomerService;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl extends GenericServiceImpl<CartItem,Long> implements CartItemService {

    private final ProductService productService;
    private final UserService userService;
    private final CustomerService customerService;

    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    public CartItemServiceImpl(ProductService productService, UserService userService, CustomerService customerService, CartItemRepository cartItemRepository, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.customerService = customerService;
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartItem> listCartItems(Customer customer) {
        return cartItemRepository.findByCustomer(customer);
    }

    @Override
    public CartItem addItems(Long productId, Integer quantity,String username) {
        Integer addQuantity=quantity;
        Optional<Product> product=productService.findById(productId);
        Optional<Customer> customer=customerService.findByUsername(username);
        CartItem cartItem=cartItemRepository.findByCustomerAndProduct(customer.get(),product.get());

        if(cartItem != null){
            addQuantity=cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addQuantity);
        }else {
            cartItem=new CartItem();
            cartItem.setPrice(product.get().getPrice() * quantity);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product.get());
            cartItem.setCustomer(customer.get());
        }

        return cartItemRepository.save(cartItem);
    }

    @Override
    public BaseResponse<Page<CreateCartItemDto>> getAll(CartItemFilterRequest filterRequest, int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            Page<CartItem> cartItems = cartItemRepository.findAllByFilter(filterRequest,pageable);
            List<CreateCartItemDto> cartItemDtos = cartItems.getContent().stream()
                    .map(cartItem -> {
                        CreateCartItemDto cartItemDto = modelMapper.map(cartItem, CreateCartItemDto.class);
                        cartItemDto.setCustomerName(cartItem.getCustomer().getFullName());
                        cartItemDto.setCustomerId(cartItem.getCustomer().getId());
                        cartItemDto.setProductId(cartItem.getProduct().getId());
                        cartItemDto.setProductName(cartItem.getProduct().getName());
                        return cartItemDto;
                    })
                    .collect(Collectors.toList());

            Page<CreateCartItemDto> pageData = new PageImpl<>(cartItemDtos, pageable, cartItems.getTotalElements());
            BaseResponse<Page<CreateCartItemDto>> response = new BaseResponse<>();
            response.setCode(200);
            response.setMessage("success");
            response.setData(pageData);
            return response;
    }


}
