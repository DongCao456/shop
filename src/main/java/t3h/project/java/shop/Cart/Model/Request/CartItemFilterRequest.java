package t3h.project.java.shop.Cart.Model.Request;

import lombok.Data;
import t3h.project.java.shop.Product.Dto.CreateProductDto;

@Data
public class CartItemFilterRequest {
    private Long id;
    private Long productId;
    private String productName;
    private String customerName;
    private Long customerId;
    private Integer quantity;
    private Float price;
}
