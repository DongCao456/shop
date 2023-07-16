package t3h.project.java.shop.User.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Repository.RoleRepository;
import t3h.project.java.shop.User.Service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role,Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<CreateRoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<CreateRoleDto> roleDtos = new ArrayList<>();
        for(Role role : roles){
            CreateRoleDto roleDto = new CreateRoleDto();
            roleDto = modelMapper.map(role, CreateRoleDto.class);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    @Override
    public Role createRole(CreateRoleDto createRoleDto) {
        Role role=new Role();
        role.name(createRoleDto.getName()).description(createRoleDto.getDescription());
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).get();
    }
}
