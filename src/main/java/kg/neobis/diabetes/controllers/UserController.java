package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.UsersWidgetsModel;
import kg.neobis.diabetes.models.WidgetModel;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
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

}
