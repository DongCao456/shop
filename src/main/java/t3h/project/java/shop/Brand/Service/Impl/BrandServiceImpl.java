package t3h.project.java.shop.Brand.Service.Impl;

//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Repository.BrandRepository;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
