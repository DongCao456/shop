package t3h.project.java.shop.Brand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Brand.Model.Brand;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

    Optional<Brand> findByName(String name);
}
