package t3h.project.java.shop.Product.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Cart.Model.CartItem;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.model.AbstractEntity;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "band_id")
    @JsonIgnore
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    @JsonIgnore
    private Category category;





//    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
//    @JoinTable(name = "product_cate",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "cate_id"))
//    private Set<Category> categories=new HashSet<>();
//
//
//    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
//    @JoinTable(name = "product_brand",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "brand_id"))
//    private Set<Brand> brands=new HashSet<>();
}
