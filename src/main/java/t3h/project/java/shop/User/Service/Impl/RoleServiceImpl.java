package t3h.project.java.shop.User.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Repository.RoleRepository;
import t3h.project.java.shop.User.Service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role,Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role createRole(CreateRoleDto createRoleDto) {
        Role role=new Role();
        role.name(createRoleDto.getName()).description(createRoleDto.getDescription());
        return roleRepository.save(role);
    }
}
