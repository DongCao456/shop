package t3h.project.java.shop.Cart.Service;

import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.User.Model.User;

import java.util.List;

public interface CartItemService extends GenericService<CartItem,Long> {

    List<CartItem> listCartItems(Customer customer);

    CartItem addItems(Long productId, Integer quantity, String name);
}
