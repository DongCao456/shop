package t3h.project.java.shop.Category.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Category.Model.Category;

import java.util.Optional;

@Repository
public interface CateRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);
    @Query(value = "SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name)")
    Category findCategoryByName(@Param("name") String name);

    @Query(value = "select c from Category c where c.id = :id")
    Category findCategoryById(@Param("id") Long id);

    @Query(value = "select c.name from Category c where lower(c.shortcut) = lower(:shortcut) ")
    String findCategoryNameByShortcut(@Param("shortcut") String shortcut);
    @Query(value = "select c.id from Category c where lower(c.name) = lower(:name) ")
    Long findIDByName(@Param("name") String name);
    @Query(value = "select c from Category c where lower(c.shortcut) = lower(:shortcut)")
    Category findCategoryByShortcut(@Param("shortcut") String shortcut);
}
