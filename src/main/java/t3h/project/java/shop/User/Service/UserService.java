package t3h.project.java.shop.User.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.web.multipart.MultipartFile;
import t3h.project.java.shop.CommonData.generic.GenericService;
import t3h.project.java.shop.CommonData.model.BaseResponse;
import t3h.project.java.shop.Product.Dto.CreateProductDto;
import t3h.project.java.shop.Product.Model.Request.ProductFilterRequest;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.Request.UserFilterRequest;
import t3h.project.java.shop.User.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User,Long> {

    User findByUsername(String name);

    void insertUser(CreateUserDto userDto);
    void updateUser(CreateUserDto userDto);
    CreateUserDto findUserById(Long id);
    void deleteUser(Long id);
    public BaseResponse importAccountUser(MultipartFile file);

    BaseResponse<Page<CreateUserDto>> getAllByFilter(UserFilterRequest filterRequest, int page, int size);
    @EntityGraph(value = "user-cart-role-orders")
    List<User> findList();
    public void saveUserRoles(Long userId, Long roleId);
}
