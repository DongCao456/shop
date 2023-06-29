package t3h.project.java.shop.Brand.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.model.AbstractEntity;
import t3h.project.java.shop.Product.Model.Product;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Brand")
public class Brand extends AbstractEntity {

    private String name;
    private String description;
    private String url;

    @ManyToMany(mappedBy = "brands")
    @JsonIgnore
    private Set<Category> categories=new HashSet<>();

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private Set<Product> products=new HashSet<>();


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
