package t3h.project.java.shop.User.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class CreateUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String district;
    private String city;
    private String phone;
    private Set<CreateRoleDto> roleDtos;
    private String roles;
}
