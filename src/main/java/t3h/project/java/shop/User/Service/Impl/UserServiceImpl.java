package t3h.project.java.shop.User.Service.Impl;

import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {
    @Override
    public User createUser(CreateUserDto createUserDto) {
        return null;
    }
}
