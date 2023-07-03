package t3h.project.java.shop.User.Service;

import org.springframework.data.jpa.repository.EntityGraph;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User,Long> {

    User createUser(CreateUserDto createUserDto);

    Optional<User> findByUsername(String name);

    @EntityGraph(value = "user-cart-role-orders")
    List<User> findList();
}
