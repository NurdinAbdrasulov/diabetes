package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.*;
import kg.neobis.diabetes.services.NormalUserPropertiesService;
import kg.neobis.diabetes.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("widgets")
@CrossOrigin
public class WidgetsController {

    private WidgetService service;
    private NormalUserPropertiesService normalService;

    @Autowired
    WidgetsController(WidgetService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WidgetModel>> getAll(){
        return ResponseEntity.ok(service.getAllWidgets());
    }


    @PostMapping("set-widgets")
    public ResponseEntity<List<WidgetModel>> setUserWidgets(@RequestBody UsersWidgetsModel model){
        return ResponseEntity.ok(service.setWidgets(model));
    }

    @PostMapping("set-normal-pressure")
    public ResponseEntity<?> setNormalPressure(@RequestBody ModelToAddNormalUserPressure model){
        return service.setNormalPressure(model);
    }

    @PostMapping("set-normal-sleep")
    public ResponseEntity<?> setNormalSleep(@RequestBody ModelToAddNormalUserSleep model){
        return ResponseEntity.ok(service.setNormalSleep(model));
    }

    @PostMapping("set-normal-sugar")
    public ResponseEntity<?> setNormalSugar(@RequestBody ModelToAddNormalUserSugar model){
        return ResponseEntity.ok(service.setNormalSugar(model));
    }


}
