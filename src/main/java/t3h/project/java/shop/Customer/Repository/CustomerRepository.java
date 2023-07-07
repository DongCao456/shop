package t3h.project.java.shop.Customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Customer.Model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUsername(String username);
    @Query(value = "select c from Customer c where c.id = :id")
    Customer findCustomerById(@Param("id") Long id);

    @Query(value = "select c from Customer c where c.username = :username")
    Customer findCustomerByUsername(@Param("username") String username);
}
