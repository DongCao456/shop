package t3h.project.java.shop.Brand.Model.Request;

import lombok.Data;

@Data
public class BrandFilterRequest {
    private Integer id;
    private String name;
    private String url;
    private String image;
    private String description;
}
