package t3h.project.java.shop.Cart.Dto;

import lombok.Data;
import lombok.ToString;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;

@Data
@ToString
public class CreateCartItemDto {
        private Long id;
        private Long productId;
        private String productName;
        private String image;
        private String userName;
        private Long userId;
        private Integer quantity;
        private Float price;
}
