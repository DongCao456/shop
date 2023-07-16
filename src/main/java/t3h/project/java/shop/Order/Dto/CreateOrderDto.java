package t3h.project.java.shop.Order.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateOrderDto {
    private Long id;
    private String username;
    private LocalDate deliveryDate;
    private Float totalCost;
    private String status;
}
