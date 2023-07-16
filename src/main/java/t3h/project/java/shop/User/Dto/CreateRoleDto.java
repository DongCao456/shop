package t3h.project.java.shop.User.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateRoleDto {
    private Long id;

    private String name;

    private String description;

    @Override
    public String toString() {
        return name;
    }
}
