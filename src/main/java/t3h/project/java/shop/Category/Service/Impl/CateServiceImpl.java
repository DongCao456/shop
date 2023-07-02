package t3h.project.java.shop.Category.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Dto.CreateCateDto;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Repository.CateRepository;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CateServiceImpl extends GenericServiceImpl<Category,Long> implements CateService {

    private final CateRepository cateRepository;

    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public CateServiceImpl(CateRepository cateRepository, BrandService brandService, ModelMapper modelMapper) {
        this.cateRepository = cateRepository;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Category> findByName(String name) {
        return cateRepository.findByName(name);
    }

    @Override
    public List<CreateCateDto> getAll() {
        List<Category> categories = cateRepository.findAll();
        List<CreateCateDto> cateDtos = new ArrayList<>();
        for(Category c : categories){
            CreateCateDto cateDto = modelMapper.map(c, CreateCateDto.class);
            cateDtos.add(cateDto);
        }
        return cateDtos;
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

    @Override
    public CreateCateDto findCateByName(String name) {
        Category category = cateRepository.findCategoryByName(name);
        CreateCateDto cateDto = modelMapper.map(category, CreateCateDto.class);
        return cateDto;
    }
}
