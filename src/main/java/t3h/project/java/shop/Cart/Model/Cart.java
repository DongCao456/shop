package t3h.project.java.shop.Cart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.User.Model.User;

@Getter
@Setter
@Entity
@Table(name = "Cart")
public class Cart extends AbstractEntity {

}
