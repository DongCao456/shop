package t3h.project.java.shop.Customer.Model;

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
@Table(name = "Role_Customer")
public class RoleCustomer extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "roleCustomers")
    @JsonIgnore
    private Set<Customer> customers=new HashSet<>();


    public RoleCustomer name(String name){
        this.name=name;
        return this;
    }
}
