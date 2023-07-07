package t3h.project.java.shop.Customer.Service.Impl;

import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Repository.CustomerRepository;
import t3h.project.java.shop.Customer.Service.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer,Long> implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer findByName(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }


}
