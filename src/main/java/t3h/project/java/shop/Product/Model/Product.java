package t3h.project.java.shop.Product.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.model.AbstractEntity;

import java.util.Set;

@Data
@Entity
@ToString
@Table(name = "Product")
public class Product extends AbstractEntity {

    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String image;

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    @ToString.Exclude
    private CartItem cart;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Brand brand;

}
