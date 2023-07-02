package t3h.project.java.shop.Brand.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Product.Model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Brand")
public class Brand extends AbstractEntity {

    private String name;
    private String description;
    private String url;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Product> products;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "category_brand",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public Brand name(String name){
        this.name=name;
        return this;
    }

    public Brand description(String description){
        this.description=description;
        return this;
    }

    public Brand url(String url){
        this.url=url;
        return this;
    }

}
