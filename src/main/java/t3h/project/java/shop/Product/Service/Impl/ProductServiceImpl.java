package t3h.project.java.shop.Product.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Repository.ProductRepository;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.Utils.MapDtoToModel;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product,Long> implements ProductService {

    private ProductRepository productRepository;
    private BrandService brandService;
    private CateService cateService;
    private MapDtoToModel<Object, Product> mapper;
    @Override
    public Product saveNew(CreateProductDto dto) {
        Product model=new Product();
        model=mapper.map(dto,model);

        Optional<Brand> optionalBrand=brandService.findByName(dto.getBrandName());
        if(optionalBrand.isPresent()){
            model.setBrand(optionalBrand.get());
        }

        Optional<Category> optionalCategory=cateService.findByName(dto.getCateName());
        if(optionalCategory.isPresent()){
            model.setCategory(optionalCategory.get());
        }

        return productRepository.save(model);
    }
}
