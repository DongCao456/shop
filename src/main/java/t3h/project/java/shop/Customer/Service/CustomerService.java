package t3h.project.java.shop.Customer.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.Customer.Model.Customer;

import java.util.Optional;

public interface CustomerService extends GenericService<Customer,Long> {

    Optional<Customer> findByUsername(String username);
    Customer findByName(String username);
    Customer findCustomerById(Long id);
}
