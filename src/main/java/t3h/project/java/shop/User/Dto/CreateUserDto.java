package t3h.project.java.shop.User.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateUserDto {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String district;
    private String city;
    private String phone;

    private Set<String> listRoles=new HashSet<>();
}
