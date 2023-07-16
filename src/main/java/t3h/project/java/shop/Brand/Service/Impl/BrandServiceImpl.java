package t3h.project.java.shop.Brand.Service.Impl;

//import org.modelmapper.ModelMapper;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.Brand.Dto.CreateBrandDto;
import t3h.project.java.shop.Brand.Model.Brand;
import t3h.project.java.shop.Brand.Model.Request.BrandFilterRequest;
import t3h.project.java.shop.Brand.Repository.BrandRepository;
import t3h.project.java.shop.Brand.Service.BrandService;
import t3h.project.java.shop.Category.Model.Category;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl extends GenericServiceImpl<Brand,Long> implements BrandService {


    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

//    @Autowired
//    private ModelMapper mapper;


    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public List<CreateBrandDto> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<CreateBrandDto> brandDtos = new ArrayList<>();
        for (Brand b:
             brands) {
            CreateBrandDto brandDto = modelMapper.map(b, CreateBrandDto.class);
            brandDtos.add(brandDto);
        }
        return brandDtos;
    }

    @Override
    public Brand createBrand(CreateBrandDto brand) {
        Brand createBrand=new Brand();
        createBrand.name(brand.getName()).description(brand.getDescription())
                .url(brand.getUrl());
        return brandRepository.save(createBrand);
    }

    @Override
    public Brand findBrandByName(String name) {
        Brand brand = brandRepository.findBrandByName(name);
        return brand;
    }

    @Override
    public Brand findBrandById(Long id) {
        return brandRepository.findBrandById(id);
    }

    @Override
    public CreateBrandDto findBrandDtoById(Long id) {
        Brand brand = brandRepository.findBrandById(id);
        CreateBrandDto brandDto = modelMapper.map(brand, CreateBrandDto.class);
        return brandDto;
    }

    @Override
    public BaseResponse<Page<CreateBrandDto>> getAll(BrandFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brands = brandRepository.findAllByFilter(filterRequest, pageable);

        List<CreateBrandDto> brandDtos = brands.getContent().stream()
                .map(brandEntity -> {
                    CreateBrandDto brandDto = modelMapper.map(brandEntity, CreateBrandDto.class);
                    return brandDto;
                })
                .collect(Collectors.toList());

        Page<CreateBrandDto> pageData = new PageImpl<>(brandDtos, pageable, brands.getTotalElements());
        BaseResponse<Page<CreateBrandDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }

    @Override
    public void update(CreateBrandDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        brandRepository.save(brand);
    }
    @SneakyThrows
    @Override
    public BaseResponse importBrandExcel(MultipartFile file) {
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

        List<Brand> brands=new ArrayList<>();

        int lastRowNum=sheet.getLastRowNum();
        int startRow=3;
        for (int i=startRow;i<=lastRowNum;i++){
            Brand brand = new Brand();
            Row row=sheet.getRow(i);
            Cell cellName=row.getCell(1);
            if(brandRepository.findBrandByName(cellName.getStringCellValue()) == null){
                brand.setName(cellName.getStringCellValue());

                Cell cellDescription=row.getCell(2);
                brand.setDescription(cellDescription.getStringCellValue());

                Cell cellImage=row.getCell(3);
                brand.setImage(cellImage.getStringCellValue());

                Cell cellUrl=row.getCell(4);
                brand.setUrl(cellUrl.getStringCellValue());

                brands.add(brand);
            }
        }
        brandRepository.saveAll(brands);
        return BaseResponse.builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build();
    }

}
