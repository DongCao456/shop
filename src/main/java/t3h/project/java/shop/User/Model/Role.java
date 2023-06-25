package t3h.project.java.shop.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import t3h.project.java.shop.CommonData.model.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Role")
public class Role extends AbstractEntity {

    private String name;

    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users=new HashSet<>();


    public Role name(String name){
        this.name=name;
        return this;
    }

    public Role description(String description){
        this.description=description;
        return this;
    }
}
