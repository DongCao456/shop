package t3h.project.java.shop.Category.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Product.Model.Product;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Category")
public class Category extends AbstractEntity {
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products=new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "cate_brand",joinColumns = @JoinColumn(name = "cate_id"),inverseJoinColumns = @JoinColumn(name = "brand_id"))
    private Set<Brand> brands=new HashSet<>();

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
