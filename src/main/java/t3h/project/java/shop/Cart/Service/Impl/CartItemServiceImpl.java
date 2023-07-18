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
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl extends GenericServiceImpl<CartItem,Long> implements CartItemService {

    private final ProductService productService;
    private final UserService userService;

    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    public CartItemServiceImpl(ProductService productService, UserService userService, CartItemRepository cartItemRepository, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartItem> listCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public CartItem addItems(Long productId, Integer quantity,String username) {
        Integer addQuantity=quantity;
        Optional<Product> product=productService.findById(productId);
        User user=userService.findByUsername(username);


        CartItem cartItem=cartItemRepository.findByUserAndProduct(user,product.get());

        if(cartItem != null){
            addQuantity=cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addQuantity);
            cartItem.setPrice(product.get().getPrice() * addQuantity);
        }else {
            cartItem=new CartItem();
            cartItem.setPrice(product.get().getPrice() * quantity);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product.get());
            cartItem.setUser(user);
        }

        return cartItemRepository.save(cartItem);
    }
    @Override
    public void removeItems(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItem updateItems(Long productId, Integer quantity, String name) {
        Optional<Product> product=productService.findById(productId);
        User user=userService.findByUsername(name);
        CartItem cartItem=cartItemRepository.findByUserAndProduct(user, product.get());
        Float price = product.get().getPrice();
        if(cartItem != null){
            cartItem.setQuantity(quantity);
            cartItem.setPrice(quantity * price);
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
                        cartItemDto.setUserName(cartItem.getUser().getFullName());
                        cartItemDto.setImage(cartItem.getProduct().getImage());
                        cartItemDto.setUserId(cartItem.getUser().getId());
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
