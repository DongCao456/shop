package t3h.project.java.shop.Cart.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Cart.Dto.CreateCartItemDto;
import t3h.project.java.shop.Cart.Model.Request.CartItemFilterRequest;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.User.Model.User;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByUser(User user);

    CartItem findByUserAndProduct(User user, Product product);

    @Query(value = "select  from Cart c where ")
    Page<CreateCartItemDto> findAll(@Param("condition")CartItemFilterRequest filterRequest, Pageable pageable);
}
