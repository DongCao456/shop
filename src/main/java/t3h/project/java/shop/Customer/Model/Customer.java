package t3h.project.java.shop.Customer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Order.Model.Order;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Customer")
public class Customer extends AbstractEntity {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String district;
    private String city;
    private String phone;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CartItem> carts=new HashSet<>();

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders=new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role",joinColumns = @JoinColumn(name = "customer_id"),inverseJoinColumns = @JoinColumn(name = "rolecustomer_id"))
    private Set<RoleCustomer> roleCustomers=new HashSet<>();

    public Customer addRoleCustomter(RoleCustomer roleCustomer){
        this.roleCustomers.add(roleCustomer);
        roleCustomer.getCustomers().add(this);
        return this;
    }
}
