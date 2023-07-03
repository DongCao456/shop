package t3h.project.java.shop.Brand.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class CreateBrandDto {
    private Integer id;
    private String name;
    private String description;
    private String url;
}
