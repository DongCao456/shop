package t3h.project.java.shop.Product.Model.Request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductFilterRequest {
    private Integer id;
    private String name;
    private Float price;
    private Integer quantity;
    private String image;
    private Integer categoryId;
    private Integer brandId;
}
