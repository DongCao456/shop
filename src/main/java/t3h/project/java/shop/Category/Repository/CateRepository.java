package t3h.project.java.shop.Category.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Category.Model.Category;

import java.util.Optional;

@Repository
public interface CateRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);
}
