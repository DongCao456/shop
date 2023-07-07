package t3h.project.java.shop.Cart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Customer.Model.Customer;
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
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    private Integer quantity;
    private Float price;
}
