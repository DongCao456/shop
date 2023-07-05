package t3h.project.java.shop.Product.Service;

import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;

import java.util.List;

public interface ProductService extends GenericService<Product,Long> {

    Product saveNew(CreateProductDto dto);

    BaseResponse importProductExcel(MultipartFile file);
}
