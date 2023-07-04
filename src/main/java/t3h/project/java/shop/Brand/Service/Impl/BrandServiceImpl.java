package t3h.project.java.shop.Brand.Service.Impl;

//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Model.Request.BrandFilterRequest;
import t3h.project.java.shop.Brand.Repository.BrandRepository;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl extends GenericServiceImpl<Brand,Long> implements BrandService {


    private final BrandRepository repository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

//    @Autowired
//    private ModelMapper mapper;


    @Override
    public Optional<Brand> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<CreateBrandDto> getAll() {
        List<Brand> brands = repository.findAll();
        List<CreateBrandDto> brandDtos = new ArrayList<>();
        for (Brand b:
             brands) {
            CreateBrandDto brandDto = modelMapper.map(b, CreateBrandDto.class);
            brandDtos.add(brandDto);
        }
        return brandDtos;
    }

    @Override
    public Brand createBrand(CreateBrandDto brand) {
        Brand createBrand=new Brand();
        createBrand.name(brand.getName()).description(brand.getDescription())
                .url(brand.getUrl());
        return repository.save(createBrand);
    }

    @Override
    public Brand findBrandByName(String name) {
        Brand brand = repository.findBrandByName(name);
        return brand;
    }

    @Override
    public Brand findBrandById(Long id) {
        return repository.findBrandById(id);
    }

    @Override
    public CreateBrandDto findBrandDtoById(Long id) {
        Brand brand = repository.findBrandById(id);
        CreateBrandDto brandDto = modelMapper.map(brand, CreateBrandDto.class);
        return brandDto;
    }

    @Override
    public BaseResponse<Page<CreateBrandDto>> getAll(BrandFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brands = repository.findAllByFilter(filterRequest, pageable);

        List<CreateBrandDto> brandDtos = brands.getContent().stream()
                .map(brandEntity -> {
                    CreateBrandDto brandDto = modelMapper.map(brandEntity, CreateBrandDto.class);
                    return brandDto;
                })
                .collect(Collectors.toList());

        Page<CreateBrandDto> pageData = new PageImpl<>(brandDtos, pageable, brands.getTotalElements());
        BaseResponse<Page<CreateBrandDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }

    @Override
    public void update(CreateBrandDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
    }
}
