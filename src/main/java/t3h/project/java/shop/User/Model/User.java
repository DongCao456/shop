package t3h.project.java.shop.User.Model;

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
@Table(name = "User")
public class User extends AbstractEntity {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String district;
    private String city;
    private String phone;


    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<CartItem> carts=new HashSet<>();

    public User addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
        return this;
    }
}
