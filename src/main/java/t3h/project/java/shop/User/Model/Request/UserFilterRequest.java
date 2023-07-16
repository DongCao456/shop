package t3h.project.java.shop.User.Model.Request;

import lombok.Data;

import java.util.Set;

@Data
public class UserFilterRequest {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String roles;
}
