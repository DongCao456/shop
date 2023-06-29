package t3h.project.java.shop.Product.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {

    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String image;
    private String brandName;
    private String cateName;
}
