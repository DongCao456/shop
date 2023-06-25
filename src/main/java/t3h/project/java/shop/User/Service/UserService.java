package t3h.project.java.shop.User.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.User;

public interface UserService extends GenericService<User,Long> {

    User createUser(CreateUserDto createUserDto);
}
