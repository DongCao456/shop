package t3h.project.java.shop.Customer.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Repository.CustomerRepository;
import t3h.project.java.shop.Customer.Service.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer,Long> implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
