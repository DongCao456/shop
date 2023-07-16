package t3h.project.java.shop.User.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.User.Model.Request.UserFilterRequest;
import t3h.project.java.shop.User.Model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    Optional<User> findByUsername(String name);
        @Query(value = "SELECT u FROM User u WHERE lower(u.username) = lower(:username)")
        User findUserByUsername(@Param("username") String username);


    public List<User> findAll();

    @Query(value = "SELECT u FROM User u " +
            "INNER JOIN u.roles r " +
            "WHERE " +
            "(:#{#condition.fullName} is null or lower(u.fullName) = lower(:#{#condition.fullName})) " +
            "AND (:#{#condition.address} is null or lower(u.address) = lower(:#{#condition.address})) " +
            "AND (:#{#condition.district} is null or lower(u.district) = lower(:#{#condition.district})) " +
            "AND (:#{#condition.city} is null or lower(u.city) = lower(:#{#condition.city})) " +
            "AND (:#{#condition.roles} is null or lower(r.name) = lower(:#{#condition.roles}))"
    )
    Page<User> findAllByFilter(@Param("condition") UserFilterRequest filterRequest, Pageable pageable);


    User findUserById(Long id);

}
