package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.UserModel;
import kg.neobis.diabetes.models.UsersWidgetsModel;
import kg.neobis.diabetes.models.WidgetModel;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    private MyUserServiceImpl service;

    @Autowired
    UserController(MyUserServiceImpl service){
        this.service = service;
    }

    @PostMapping("setWidgets")
    public ResponseEntity<List<WidgetModel>> setUserWidgets(@RequestBody UsersWidgetsModel model){
        return ResponseEntity.ok(service.setWidgets(model));
    }

    @GetMapping("getAllUsers")
    public Page<UserModel> getAllUsers(@RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "15") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy){
        return service.getAllUsers(pageNo, pageSize, sortBy);
    }

}
