package t3h.project.java.shop.Cart.Service;

import org.springframework.data.domain.Page;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.User.Model.User;

import java.util.List;

public interface CartItemService extends GenericService<CartItem,Long> {

    List<CartItem> listCartItems(User user);

    CartItem addItems(Long productId, Integer quantity, String name);
}
