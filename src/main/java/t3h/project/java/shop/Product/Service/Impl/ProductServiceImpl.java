package t3h.project.java.shop.Product.Service.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.Product.Repository.ProductRepository;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.Utils.MapDtoToModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product,Long> implements ProductService {

    private ProductRepository productRepository;
    private BrandService brandService;
    private CateService cateService;
    private MapDtoToModel<Object, Product> mapper;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, BrandService brandService, CateService cateService, MapDtoToModel<Object, Product> mapper, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.cateService = cateService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product saveNew(CreateProductDto dto) {
        Product model=new Product();
        model=mapper.map(dto,model);

        Optional<Brand> optionalBrand=brandService.findByName(dto.getBrandName());
        if(optionalBrand.isPresent()){
//            model.setBrands();
            /*
            MODIFY THIS !!!
             */
        }

        Optional<Category> optionalCategory=cateService.findByName(dto.getCateName());
        if(optionalCategory.isPresent()){
            model.setCategory(optionalCategory.get());
        }

        return productRepository.save(model);
    }

    @Override
    public BaseResponse<Page<CreateProductDto>> getAllByBrand(ProductFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productEntities = productRepository.findAllByBrand(filterRequest,pageable);

        List<CreateProductDto> productDTOs = productEntities.getContent().stream()
                .map(productEntity -> {
                    CreateProductDto productDto = modelMapper.map(productEntity, CreateProductDto.class);
                    productDto.setBrandName(productEntity.getBrand().getName());
                    productDto.setCateName(productEntity.getCategory().getName());
                    productDto.setImage(productEntity.getImage());
                    return productDto;
                })
                .collect(Collectors.toList());

        Page<CreateProductDto> pageData = new PageImpl<>(productDTOs, pageable, productEntities.getTotalElements());
        BaseResponse<Page<CreateProductDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }

    @Override
    public BaseResponse<Page<CreateProductDto>> getAllByCategory(ProductFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productEntities = productRepository.findAllByCategory(filterRequest,pageable);
        List<CreateProductDto> productDTOs = productEntities.getContent().stream()
                .map(productEntity -> {
                    CreateProductDto productDto = modelMapper.map(productEntity, CreateProductDto.class);
                    productDto.setBrandName(productEntity.getBrand().getName());
                    productDto.setImage(productEntity.getImage());
                    productDto.setCateName(productEntity.getCategory().getName());
                    return productDto;
                })
                .collect(Collectors.toList());

        Page<CreateProductDto> pageData = new PageImpl<>(productDTOs, pageable, productEntities.getTotalElements());
        BaseResponse<Page<CreateProductDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }

    @Override
    public BaseResponse<Page<CreateProductDto>> getAll(ProductFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productEntities = productRepository.findAllByFilter(filterRequest, pageable);

        List<CreateProductDto> productDTOs = productEntities.getContent().stream()
                .map(productEntity -> {
                    CreateProductDto productDto = modelMapper.map(productEntity, CreateProductDto.class);
                    productDto.setBrandName(productEntity.getBrand().getName());
                    productDto.setImage(productEntity.getImage());
                    productDto.setCateName(productEntity.getCategory().getName());
                    return productDto;
                })
                .collect(Collectors.toList());

        Page<CreateProductDto> pageData = new PageImpl<>(productDTOs, pageable, productEntities.getTotalElements());
        BaseResponse<Page<CreateProductDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }
}
