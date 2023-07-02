package t3h.project.java.shop.Category.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateCateDto {
    private Integer id;
    private String name;
    private String description;
    private Set<String> listBrandsName=new HashSet<>();
}
