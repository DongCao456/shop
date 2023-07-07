package t3h.project.java.shop.CustomerSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import t3h.project.java.shop.Customer.Model.Customer;
import t3h.project.java.shop.Customer.Model.RoleCustomer;
import t3h.project.java.shop.Customer.Repository.CustomerRepository;
import t3h.project.java.shop.Customer.Service.CustomerService;
import t3h.project.java.shop.CustomerSecurity.Dto.CustomerDetailsDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;


public class CustomerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer=service.findByUsername(username);
        if(!customer.isPresent())
            throw new UsernameNotFoundException("Email Is Not Valid");

        Set<GrantedAuthority> authorities=getAuthorities(customer.get().getRoleCustomers());
        return new CustomerDetailsDto(customer.get().getUsername(),customer.get().getPassword(),authorities);
    }

    public Set<GrantedAuthority> getAuthorities(Set<RoleCustomer> roleCustomers){
        Set<GrantedAuthority> authorities=new HashSet<>();
        Iterator<RoleCustomer> iterator= roleCustomers.iterator();
        while ((iterator.hasNext())){
            authorities.add(new SimpleGrantedAuthority(iterator.next().getName()));
        }
        return authorities;
    }
}
