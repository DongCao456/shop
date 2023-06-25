package t3h.project.java.shop.User.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Model.Role;

public interface RoleService extends GenericService<Role,Long> {

    Role findByName(String name);

    Role createRole(CreateRoleDto createRoleDto);
}
