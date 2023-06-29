package t3h.project.java.shop.User.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User,Long> {

    User createUser(CreateUserDto createUserDto);

    Optional<User> findByUsername(String name);


}
