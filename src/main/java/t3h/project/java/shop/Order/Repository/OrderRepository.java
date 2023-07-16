package t3h.project.java.shop.Order.Repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Order.Model.Order;
import t3h.project.java.shop.Order.Model.Request.OrderFilterRequest;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "SELECT o from Order o " +
            "INNER JOIN o.user u " +
            "WHERE " +
            "(lower(u.username) = :#{#condition.username} " +
            "OR (u.id = :#{#condition.userId})) " +
            "AND (:#{#condition.deliveryDate} is null or o.deliveryDate = :#{#condition.deliveryDate}) " +
            "AND (:#{#condition.totalCost} is null or o.totalCost = :#{#condition.totalCost}) " +
            "AND (:#{#condition.status} is null or lower(o.status) = lower(:#{#condition.status}))"
    )
    Page<Order> findAllOrderByUsername(@Param("condition") OrderFilterRequest filterRequest, Pageable pageable);
}
