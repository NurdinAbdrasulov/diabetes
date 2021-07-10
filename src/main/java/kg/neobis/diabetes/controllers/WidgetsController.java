package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.WidgetModel;
import kg.neobis.diabetes.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("widgets")
@CrossOrigin
public class WidgetsController {

    private WidgetService service;

    @Autowired
    WidgetsController(WidgetService service){
        this.service = service;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<WidgetModel>> getAll(){
        return ResponseEntity.ok(service.getAllWidgets());
    }


}
