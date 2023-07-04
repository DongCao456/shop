package t3h.project.java.shop.Brand.Service;

import org.springframework.data.domain.Page;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Model.Request.BrandFilterRequest;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

import java.util.List;
import java.util.Optional;

public interface BrandService extends GenericService<Brand,Long> {
    Optional<Brand> findByName(String name);
    List<CreateBrandDto> getAll();

    Brand createBrand(CreateBrandDto brand);
    Brand findBrandByName(String name);
    Brand findBrandById(Long id);
    CreateBrandDto findBrandDtoById(Long id);
    BaseResponse<Page<CreateBrandDto>> getAll(BrandFilterRequest filterRequest, int page, int size);
    void update(CreateBrandDto brandDto);
}
