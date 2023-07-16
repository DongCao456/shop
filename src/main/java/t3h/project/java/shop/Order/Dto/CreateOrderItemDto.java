package t3h.project.java.shop.Order.Dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateOrderItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private String image;
    private Long orderId;
    private Integer quantity;
    private Float price;
    private Float priceOfOne;
}
