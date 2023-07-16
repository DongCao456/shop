package t3h.project.java.shop.Order.Model.Request;

import lombok.Data;

@Data
public class OrderItemFilterRequest {
    private Long id;
    private Long productId;
    private String productName;
    private Long orderId;
    private Integer quantity;
    private Float price;
    private Float priceOfOne;
}
