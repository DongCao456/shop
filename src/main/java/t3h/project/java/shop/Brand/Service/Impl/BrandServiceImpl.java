package t3h.project.java.shop.Brand.Service.Impl;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Repository.BrandRepository;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;

import java.util.Optional;

@Service
public class BrandServiceImpl extends GenericServiceImpl<Brand,Long> implements BrandService {

    @Autowired
    private BrandRepository repository;
//    @Autowired
//    private ModelMapper mapper;


    @Override
    public Optional<Brand> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Brand createBrand(CreateBrandDto brand) {
        Brand createBrand=new Brand();
        createBrand.name(brand.getName()).description(brand.getDescription())
                .url(brand.getUrl());
        return repository.save(createBrand);
    }
}
