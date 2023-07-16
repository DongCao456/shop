package t3h.project.java.shop.User.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService extends GenericService<Role,Long> {

    Optional<Role> findByName(String name);
    List<CreateRoleDto> getAll();

    Role createRole(CreateRoleDto createRoleDto);
    Role findRoleByName(String name);
}
