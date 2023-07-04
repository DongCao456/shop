package t3h.project.java.shop.Brand.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Data
@ToString
public class CreateBrandDto {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String url;
}
