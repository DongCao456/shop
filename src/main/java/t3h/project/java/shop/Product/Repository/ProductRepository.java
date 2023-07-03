package t3h.project.java.shop.Product.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p FROM Product p " +
            "INNER JOIN p.category c " +
            "WHERE (:#{#condition.categoryId} is null or c.id = :#{#condition.categoryId} )")
    Page<Product> findAllByCategory(@Param("condition") ProductFilterRequest filterRequest,
                                    Pageable pageable);
    @Query(value = "SELECT p FROM Product p " +
            "INNER JOIN p.brand b " +
            "WHERE (:#{#condition.brandId} is null or b.id = :#{#condition.brandId} )")
    Page<Product> findAllByBrand(@Param("condition") ProductFilterRequest filterRequest,
                                    Pageable pageable);
    @Query(value = "SELECT p FROM Product p " +
            "LEFT JOIN p.category c " +
            "LEFT JOIN p.brand b " +
            " WHERE " +
            " (:#{#condition.name} is null or lower(p.name) = :#{#condition.name}) " +
            "AND (:#{#condition.price} is null or p.price = :#{#condition.price} )" +
            "AND (:#{#condition.status} is null or p.quantity = :#{#condition.quantity} )" +
            "AND (:#{#condition.categoryId} is null or c.id = :#{#condition.categoryId} )" +
            "AND (:#{#condition.sizeId} is null or b.id = :#{#condition.brandId} )"
    )
    Page<Product> findAllByFilter(@Param("condition") ProductFilterRequest filterRequest, Pageable pageable);
    @Query(value = "select p from Product p where p.id = :id")
    Product findProductById(@Param("id") Long id);
}
