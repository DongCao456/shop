package t3h.project.java.shop.Order.Model.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderFilterRequest {
    private Long id;
    private String username;
    private Long userId;
    private LocalDate deliveryDate;
    private Float totalCost;
    private String status;
}
