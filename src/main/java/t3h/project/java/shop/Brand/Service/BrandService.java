package t3h.project.java.shop.Brand.Service;

import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.CommonData.generic.GenericService;

import java.util.Optional;

public interface BrandService extends GenericService<Brand,Long> {
    Optional<Brand> findByName(String name);

    Brand createBrand(CreateBrandDto brand);
}
