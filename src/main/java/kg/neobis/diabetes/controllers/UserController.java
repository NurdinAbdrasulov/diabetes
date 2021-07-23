package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.UserModel;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    private MyUserServiceImpl service;

    @Autowired
    UserController(MyUserServiceImpl service){
        this.service = service;
    }



    @GetMapping("getAllUsers")
    public Page<UserModel> getAllUsers(@RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "15") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy){
        return service.getAllUsers(pageNo, pageSize, sortBy);
    }

}
