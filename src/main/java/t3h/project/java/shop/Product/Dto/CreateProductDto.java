package t3h.project.java.shop.Product.Dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateProductDto {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String image;
    private String brandName;
    private String cateName;
}
