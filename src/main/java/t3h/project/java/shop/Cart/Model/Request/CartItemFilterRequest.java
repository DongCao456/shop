package t3h.project.java.shop.Cart.Model.Request;

import t3h.project.java.shop.Product.Dto.CreateProductDto;

public class CartItemFilterRequest {
    private Integer id;
    private Integer cartId;
    private Integer userId;
    private Integer quantity;
    private CreateProductDto productDto;
    private Float price;
}
