package t3h.project.java.shop.Category.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Product.Model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Category")
@ToString
public class Category extends AbstractEntity {
    private String name;
    private String description;
    private String shortcut;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Product> products;

    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<Brand> brands;

    public Category addBrand(Brand brand){
        this.brands.add(brand);
        brand.getCategories().add(this);
        return this;
    }

    public Category name(String name){
        this.name=name;
        return this;
    }

    public Category description(String description){
        this.description=description;
        return this;
    }


}
