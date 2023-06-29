package t3h.project.java.shop.Product.Service;

import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;

public interface ProductService extends GenericService<Product,Long> {

    Product saveNew(CreateProductDto dto);
}
