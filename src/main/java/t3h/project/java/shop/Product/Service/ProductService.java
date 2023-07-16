package t3h.project.java.shop.Product.Service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;

import java.util.List;

public interface ProductService extends GenericService<Product,Long> {

    Product saveNew(CreateProductDto dto);
    public Resource export();
    BaseResponse importProductExcel(MultipartFile file);
//    public Resource export();
    BaseResponse<Page<CreateProductDto>> getAllByBrand(ProductFilterRequest filterRequest, int page, int size);
    BaseResponse<Page<CreateProductDto>> getAllByCategory(ProductFilterRequest filterRequest, int page, int size);
    List<CreateProductDto> getAllByCategoryName(String categoryName);
    BaseResponse<Page<CreateProductDto>> getAll(ProductFilterRequest filterRequest, int page, int size);
    CreateProductDto findProductById(Long id);
    public void insertProduct(CreateProductDto productDto);
    public void deleteById(Long id);
    public void updateProduct(CreateProductDto productDto);
    ProductFilterRequest findOneById(Long id);
}
