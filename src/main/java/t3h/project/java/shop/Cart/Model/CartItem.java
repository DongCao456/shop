package t3h.project.java.shop.Cart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.User.Model.User;

@Getter
@Setter
@Entity
@Table(name = "Cart_Item")
public class CartItem extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_product",
            joinColumns =
                    { @JoinColumn(name = "cart_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "product_id", referencedColumnName = "id") })
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private Integer quantity;
    private Float price;
}
