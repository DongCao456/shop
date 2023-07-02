package t3h.project.java.shop.Product.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.model.AbstractEntity;

import java.util.Set;

@Data
@Entity
@Table(name = "Product")
public class Product extends AbstractEntity {

    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String image;

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private CartItem cart;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    private Brand brand;

}
