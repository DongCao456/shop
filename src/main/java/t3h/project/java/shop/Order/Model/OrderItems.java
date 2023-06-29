package t3h.project.java.shop.Order.Model;

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
@Table(name = "OrderItem")
public class OrderItems extends AbstractEntity {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "orderdetail_product",
            joinColumns =
                    { @JoinColumn(name = "orderdetail_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "product_id", referencedColumnName = "id") })
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    private Integer quantity;
    private Float price;
}
