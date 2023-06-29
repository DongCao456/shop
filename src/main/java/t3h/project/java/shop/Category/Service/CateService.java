package t3h.project.java.shop.Category.Service;

import t3h.project.java.shop.Category.Dto.CreateCateDto;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Repository.CateRepository;
import t3h.project.java.shop.CommonData.generic.GenericService;

import java.util.Optional;

public interface CateService extends GenericService<Category,Long> {

    Optional<Category> findByName(String name);


    Category createCateDto(CreateCateDto createCateDto);
}
