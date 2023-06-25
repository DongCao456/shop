package t3h.project.java.shop.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<Object> list(){
        List<Role> roles=roleService.findAll();
        return ResponseHandler.getResponse(roles, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CreateRoleDto createRoleDto){
        Role role=roleService.createRole(createRoleDto);
        return ResponseHandler.getResponse(role, HttpStatus.OK);
    }
}
