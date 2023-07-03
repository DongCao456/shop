package t3h.project.java.shop.User.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Repository.RoleRepository;
import t3h.project.java.shop.User.Repository.UserRepository;
import t3h.project.java.shop.User.Service.RoleService;
import t3h.project.java.shop.User.Service.UserService;
import t3h.project.java.shop.Utils.MapDtoToModel;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {


    private UserRepository userRepository;

    private RoleService roleService;

    private MapDtoToModel<Object, User> mapper;

    @Override
    public User createUser(CreateUserDto createUserDto) {
        User user=new User();
        user=mapper.map(createUserDto,user);

        for (String s: createUserDto.getListRoles()
             ) {
            Optional<Role> optionalRole= roleService.findByName(s);
            if (optionalRole.isPresent()){
                user.addRole(optionalRole.get());
            }
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findList() {
        return userRepository.findAll();
    }
}
