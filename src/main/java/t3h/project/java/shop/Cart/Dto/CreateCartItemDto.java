package t3h.project.java.shop.Cart.Dto;

import lombok.Data;
import lombok.ToString;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;

@Data
@ToString
public class CreateCartItemDto {
        private Integer id;
        private Integer cartId;
        private Integer userId;
        private Integer quantity;
        private CreateProductDto productDto;
        private Float price;
}
