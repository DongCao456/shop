package t3h.project.java.shop.Order.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.User.Model.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "OrderCC")
public class Order extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderItems> orderItemsList=new HashSet<>();

    private LocalDate deliveryDate;
    private Float totalCost;
    private String status;

    public Order addItems(OrderItems orderItems){
        orderItems.setOrder(this);
        this.orderItemsList.add(orderItems);

        return this;
    }

}
