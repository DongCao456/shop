package t3h.project.java.shop.Order.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Model.OrderItems;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Order.Model.Request.OrderItemFilterRequest;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {

    @Query(value = "SELECT oi from OrderItems oi " +
            "INNER JOIN oi.order o " +
            "INNER JOIN oi.product p " +
            "WHERE " +
            "(o.id = :#{#condition.orderId}) " +
            "AND (:#{#condition.quantity} is null or oi.quantity = :#{#condition.quantity}) " +
            "AND (:#{#condition.priceOfOne} is null or p.price = :#{#condition.priceOfOne}) " +
            "AND (:#{#condition.productName} is null or lower(p.name) = :#{#condition.productName})"
    )
    Page<OrderItems> findAllOrderItemsByOrderId(@Param("condition") OrderItemFilterRequest filterRequest, Pageable pageable);

}
