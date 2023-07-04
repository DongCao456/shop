package t3h.project.java.shop.Brand.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Model.Request.BrandFilterRequest;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

    Optional<Brand> findByName(String name);
    @Query(value = "SELECT b FROM Brand b WHERE LOWER(b.name) = LOWER(:name)")
    Brand findBrandByName(@Param("name") String name);

    @Query(value = "select b from Brand b where b.id = :id")
    Brand findBrandById(@Param("id") Long id);

    @Query(value = "SELECT b from Brand b " +
            "WHERE " +
            "(:#{#condition.name} is null or lower(b.name) = :#{#condition.name}) " +
            "AND (:#{#condition.url} is null or lower(b.url) = :#{#condition.url}) " +
            "AND (:#{#condition.description} is null or lower(b.description) = :#{#condition.description})"
    )
    Page<Brand> findAllByFilter(@Param("condition")BrandFilterRequest filterRequest, Pageable pageable);

}
