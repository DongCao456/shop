package t3h.project.java.shop.User.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t3h.project.java.shop.CommonData.model.ResponseHandler;
import t3h.project.java.shop.User.Dto.CreateUserDto;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Repository.UserRepository;
import t3h.project.java.shop.User.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        List<User> list=userService.findList();
        return ResponseHandler.getResponse(list, HttpStatus.OK);
    }

//    @PostMapping("/save")
//    public ResponseEntity<Object> save(@RequestBody CreateUserDto createUserDto){
//        User user= userService.createUser(createUserDto);
//        return ResponseHandler.getResponse(user, HttpStatus.OK);
//    }
}
