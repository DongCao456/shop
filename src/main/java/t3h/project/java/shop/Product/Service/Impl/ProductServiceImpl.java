package t3h.project.java.shop.Product.Service.Impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Repository.BrandRepository;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Dto.CreateCateDto;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ResourceUtils.getFile;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product,Long> implements ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CateService cateService;
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
    public void insertProduct(CreateProductDto productDto) {
        Brand brand = brandService.findBrandByName(productDto.getBrandName());
        Category category = cateService.findCateByName(productDto.getCateName());
        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        System.out.println(category);
        product.setBrand(brand);
        System.out.println(brand);
        System.out.println(product);
        productRepository.save(product);
    }
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

            String name = cellName.getStringCellValue();

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
            product.setBrand(optionalBrand.get());

            Cell cellCategory=row.getCell(6);
            Optional<Category> optionalCategory=cateService.findByName(cellCategory.getStringCellValue());
            product.setCategory(optionalCategory.get());

            Cell cellImage=row.getCell(7);
            product.setImage(cellImage.getStringCellValue());
            if(productRepository.findProductByName(name) != null){
               Product p = productRepository.findProductByName(name);
               p.setQuantity(p.getQuantity() + product.getQuantity());
               productRepository.save(p);
            }
            else{
                products.add(product);
            }
        }

        productRepository.saveAll(products);

        return BaseResponse.builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build();
    }

    private static Workbook getWorkbook(File file) {
        Workbook workbook = null;

        try {
            workbook = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return workbook;
    }

    private static File getFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile("C:\\Users\\Admin\\Desktop\\ProductExport.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    private static CellStyle getCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        cellStyle.setFont(font);
        return cellStyle;
    }

    private static void writeDataToRow(CellStyle cellStyle, int i, Row row, Product product) {
        System.out.println(product);
        Cell cellNumber = row.createCell(0);
        cellNumber.setCellValue(i + 1);
        cellNumber.setCellStyle(cellStyle);

        Cell cellName = row.createCell(1);
        cellName.setCellValue(product.getName());
        cellName.setCellStyle(cellStyle);

        Cell cellDesc = row.createCell(2);
        cellDesc.setCellValue(product.getDescription());
        cellDesc.setCellStyle(cellStyle);

        Cell cellPrice = row.createCell(3);
        cellPrice.setCellValue(product.getPrice());
        cellPrice.setCellStyle(cellStyle);

        Cell cellQuantity = row.createCell(4);
        cellQuantity.setCellValue(product.getQuantity());
        cellQuantity.setCellStyle(cellStyle);

        Cell cellBrand = row.createCell(5);
        cellBrand.setCellValue(product.getBrand().getName());
        cellBrand.setCellStyle(cellStyle);

        Cell cellCate = row.createCell(6);
        cellCate.setCellValue(product.getCategory().getName());
        cellCate.setCellStyle(cellStyle);

        Cell cellImage = row.createCell(7);
        cellImage.setCellValue(product.getImage());
        cellImage.setCellStyle(cellStyle);

    }
    private static void writeContentToFile(File file, Workbook workbook) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getBytes(File file) {
        Path path = Paths.get(file.toURI());
        byte[] bytes =new byte[0];
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
    @Override
    public Resource export() {

        File file = getFile();

        List<Product> products = productRepository.findAll();

        Workbook workbook = getWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        CellStyle cellStyle = getCellStyle(workbook);
        int startRow = 3;
        for (int i = 0; i < products.size(); i++) {
            Row row = sheet.createRow(startRow++);
            Product product = products.get(i);
            writeDataToRow(cellStyle, i, row, product);
        }

        writeContentToFile(file, workbook);

        byte[] bytes = getBytes(file);

        Resource resource = new ByteArrayResource(bytes);
        return resource;
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
        System.out.println(filterRequest.getCategoryId());
        Page<Product> productEntities = productRepository.findAllByCategory(filterRequest,pageable);
        List<CreateProductDto> productDTOs = productEntities.getContent().stream()
                .map(productEntity -> {
                    CreateProductDto productDto = modelMapper.map(productEntity, CreateProductDto.class);
//                    System.out.println(productEntity.getBrand().getName());
                    productDto.setBrandName(productEntity.getBrand().getName());
                    productDto.setCateName(productEntity.getCategory().getName());
                    System.out.println(productDto);
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
    public List<CreateProductDto> getAllByCategoryName(String categoryName) {
        List<Product> products = productRepository.getProductsByCategoryName(categoryName);
        List<CreateProductDto> productDtos = products.stream().map(product -> {
            CreateProductDto productDto = modelMapper.map(product, CreateProductDto.class);
            productDto.setCateName(product.getCategory().getName());
            productDto.setBrandName(product.getBrand().getName());
            return productDto;
        }).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public BaseResponse<Page<CreateProductDto>> getAll(ProductFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productEntities = productRepository.findAllByFilter(filterRequest, pageable);

        List<CreateProductDto> productDTOs = productEntities.getContent().stream()
                .map(productEntity -> {
                    CreateProductDto productDto = modelMapper.map(productEntity, CreateProductDto.class);
                    productDto.setCateName(productEntity.getCategory().getName());
                    productDto.setBrandName(productEntity.getBrand().getName());
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
    public CreateProductDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);
        CreateProductDto productDto = modelMapper.map(product, CreateProductDto.class);
        productDto.setBrandName(brandService.findBrandById(product.getBrand().getId()).getName());
        productDto.setCateName(cateService.findCateById(product.getCategory().getId()).getName());
        return productDto;
    }
    @Override
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(CreateProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setId(productDto.getId());
        product.setBrand(brandService.findBrandByName(productDto.getBrandName()));
        product.setCategory(cateService.findCateByName(productDto.getCateName()));
        productRepository.save(product);
    }

    @Override
    public ProductFilterRequest findOneById(Long id) {
        Product product = productRepository.findProductById(id);
        ProductFilterRequest filterRequest = modelMapper.map(product, ProductFilterRequest.class);
        return filterRequest;
    }

}
