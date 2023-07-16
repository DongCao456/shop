package t3h.project.java.shop.User.Service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.CommonData.generic.GenericServiceImpl;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.User.Dto.CreateRoleDto;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.Request.UserFilterRequest;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Repository.UserRepository;
import t3h.project.java.shop.User.Service.RoleService;
import t3h.project.java.shop.User.Service.UserService;
import t3h.project.java.shop.Utils.MapDtoToModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {


    private final UserRepository userRepository;

    private final RoleService roleService;

    private final MapDtoToModel<Object, User> mapper;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, MapDtoToModel<Object, User> mapper, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveUserRoles(Long userId, Long roleId) {
        User user = entityManager.find(User.class, userId);
        Role role = entityManager.find(Role.class, roleId);

        if (user != null && role != null) {
            user.getRoles().add(role);
        }
    }

//    @Override
//    public User createUser(CreateUserDto createUserDto) {
//        User user=new User();
//        user=mapper.map(createUserDto,user);
//
//        for (String s: createUserDto.getRoles()
//             ) {
//            Optional<Role> optionalRole= roleService.findByName(s);
//            if (optionalRole.isPresent()){
//                user.addRole(optionalRole.get());
//            }
//        }
//        return userRepository.save(user);
//    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public void insertUser(CreateUserDto userDto) {
        System.out.println(userDto.getRoles());
        if(userRepository.findUserByUsername(userDto.getUsername()) == null){
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                User user = modelMapper.map(userDto, User.class);
                String roleName = userDto.getRoles();
                Role role = roleService.findRoleByName(roleName);
                if(user.getRoles() == null){
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);
                    user.setRoles(roles);
                }
                else{
                    user.getRoles().add(role);
                }
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(CreateUserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        String roleName = userDto.getRoles();
        Role role = roleService.findRoleByName(roleName);
        user.getRoles().clear();
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public CreateUserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        CreateUserDto userDto = modelMapper.map(user, CreateUserDto.class);
        return userDto;
    }
    @SneakyThrows
    @Override
    public BaseResponse importAccountUser(MultipartFile file) {
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

        List<User> users=new ArrayList<>();

        int lastRowNum=sheet.getLastRowNum();
        int startRow=3;
        for (int i=startRow;i<=lastRowNum;i++){
            Row row=sheet.getRow(i);

            Cell cellUserName=row.getCell(1);

            User userFind = userRepository.findUserByUsername(cellUserName.getStringCellValue());
            if(userFind == null){
                User user = new User();

                user.setUsername(cellUserName.getStringCellValue());

                Cell cellPassword=row.getCell(2);
                String encryptedPassword = passwordEncoder.encode(cellPassword.getStringCellValue());
                user.setPassword(encryptedPassword);

                Cell cellEmail=row.getCell(3);
                user.setEmail(cellEmail.getStringCellValue());

                Cell cellPhone=row.getCell(4);
                user.setPhone(cellPhone.getStringCellValue());

                Cell cellFullName=row.getCell(5);
                user.setFullName(cellFullName.getStringCellValue());

                Cell cellAddress=row.getCell(6);
                user.setAddress(cellAddress.getStringCellValue());

                Cell cellDistrict=row.getCell(7);
                user.setDistrict(cellDistrict.getStringCellValue());

                Cell cellCity=row.getCell(8);
                user.setCity(cellCity.getStringCellValue());

                Role role = roleService.findRoleByName("user");
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                user.setRoles(roles);
                users.add(user);
            }
        }

        userRepository.saveAll(users);

        return BaseResponse.builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build();
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public BaseResponse<Page<CreateUserDto>> getAllByFilter(UserFilterRequest filterRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userEntities = userRepository.findAllByFilter(filterRequest, pageable);

        List<CreateUserDto> userDtos = userEntities.getContent().stream()
                .map(userEntity -> {
                    CreateUserDto userDto = modelMapper.map(userEntity, CreateUserDto.class);
                    for (Role role:
                         userEntity.getRoles()) {
                        CreateRoleDto roleDto = new CreateRoleDto();
                        roleDto.setId(role.getId());
                        roleDto.setName(role.getName());
                        if(userDto.getRoleDtos() == null){
                            Set<CreateRoleDto> roleDtos = new HashSet<>();
                            roleDtos.add(roleDto);
                            userDto.setRoleDtos(roleDtos);
                        }
                        else{
                            userDto.getRoleDtos().add(roleDto);
                        }
                    }
                    userDto.setRoles(userDto.getRoleDtos().toString());
                    System.out.println(userDto.getRoles());
                    return userDto;
                })
                .collect(Collectors.toList());

        Page<CreateUserDto> pageData = new PageImpl<>(userDtos, pageable, userEntities.getTotalElements());
        BaseResponse<Page<CreateUserDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageData);
        return response;
    }

    @Override
    public List<User> findList() {
        return userRepository.findAll();
    }
}
