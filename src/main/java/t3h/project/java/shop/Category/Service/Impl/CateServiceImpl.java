package t3h.project.java.shop.Category.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Dto.CreateCateDto;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Repository.CateRepository;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Utils.MapDtoToModel;

import java.util.Optional;

@Service
public class CateServiceImpl extends GenericServiceImpl<Category,Long> implements CateService {

    @Autowired
    private CateRepository cateRepository;

    @Autowired
    private BrandService brandService;
    @Autowired
    private MapDtoToModel<Object, Category> mapper;

    @Override
    public Optional<Category> findByName(String name) {
        return cateRepository.findByName(name);
    }

    @Override
    public Category createCateDto(CreateCateDto createCateDto) {
        Category category=new Category();
        //category=mapper.map(createCateDto,category);
        category.name(createCateDto.getName()).description(createCateDto.getDescription());

        for(String s : createCateDto.getListBrandsName()){
            Optional<Brand> brand=brandService.findByName(s);
            if(brand.isPresent())
                category.addBrand(brand.get());
        }
        return cateRepository.save(category);
    }
}
