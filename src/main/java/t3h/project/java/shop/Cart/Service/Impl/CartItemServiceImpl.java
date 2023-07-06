package t3h.project.java.shop.Cart.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Repository.CartItemRepository;
import t3h.project.java.shop.Cart.Service.CartItemService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Repository.ProductRepository;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl extends GenericServiceImpl<CartItem,Long> implements CartItemService {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<CartItem> listCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public CartItem addItems(Long productId, Integer quantity,String name) {
        Integer addQuantity = quantity;
        Optional<Product> product=productService.findById(productId);
        Optional<User> user=userService.findByUsername(name);
        CartItem cartItem=cartItemRepository.findByUserAndProduct(user.get(),product.get());

        if(cartItem != null){
            addQuantity=cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addQuantity);
        }else {
            cartItem=new CartItem();
            cartItem.setPrice(product.get().getPrice() * quantity);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product.get());
            cartItem.setUser(user.get());
        }

        return cartItemRepository.save(cartItem);
    }


}
