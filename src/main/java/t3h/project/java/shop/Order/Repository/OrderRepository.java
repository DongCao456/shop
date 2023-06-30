package t3h.project.java.shop.Order.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Order.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}