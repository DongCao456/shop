package t3h.project.java.shop.Product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Product.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
