package t3h.project.java.shop.Product.Service.Impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.Category.Service.CateService;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Product;
import t3h.project.java.shop.Product.Repository.ProductRepository;
import t3h.project.java.shop.Product.Service.ProductService;
import t3h.project.java.shop.Utils.MapDtoToModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

    @SneakyThrows
    @Override
    public BaseResponse importProductExcel(MultipartFile file) {
        InputStream inputStream= null;
        try {
            inputStream=file.getInputStream();
        }catch (IOException e){
            return BaseResponse.builder().code(HttpStatus.BAD_REQUEST.value()).build();
        }

        Workbook workbook= null;
        try {
            workbook=new XSSFWorkbook(inputStream);
        }catch (IOException e){
            return BaseResponse.builder().code(HttpStatus.BAD_REQUEST.value()).build();
        }

        Sheet sheet=workbook.getSheetAt(0);

        List<Product> products=new ArrayList<>();

        int lastRowNum=sheet.getLastRowNum();
        int startRow=3;
        for (int i=startRow;i<=lastRowNum;i++){
            Product product = new Product();
            Row row=sheet.getRow(i);

            Cell cellName=row.getCell(1);
            product.setName(cellName.getStringCellValue());

            Cell cellDescription=row.getCell(2);
            product.setDescription(cellDescription.getStringCellValue());

            Cell cellPrice=row.getCell(3);
            Double priceDouble=cellPrice.getNumericCellValue();
            product.setPrice(priceDouble.floatValue());

            Cell cellQuantity=row.getCell(4);
            Double quantityDouble=cellQuantity.getNumericCellValue();
            product.setQuantity(quantityDouble.intValue());

            Cell cellBrand=row.getCell(5);
            Optional<Brand> optionalBrand=brandService.findByName(cellBrand.getStringCellValue());
            if (optionalBrand.isPresent()){
                product.setBrand(optionalBrand.get());
            }

            Cell cellCategory=row.getCell(6);
            Optional<Category> optionalCategory=cateService.findByName(cellCategory.getStringCellValue());
            if(optionalCategory.isPresent()){
                product.setCategory(optionalCategory.get());
            }

            Cell cellImage=row.getCell(7);
            if(cellImage == null){
                product.setImage(" ");
            }
            else {
                product.setImage(cellImage.getStringCellValue());
            }


            products.add(product);
        }

        productRepository.saveAll(products);

        return BaseResponse.builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build();
    }
}
