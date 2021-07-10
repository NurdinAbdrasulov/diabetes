package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.AgeStatisticsModel;
import kg.neobis.diabetes.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistics")
@CrossOrigin
public class StatisticsController {

    private final StatisticsService service;

    @Autowired
    StatisticsController(StatisticsService service){
        this.service = service;
    }

    @GetMapping("genders")
    public ResponseEntity genders(){
        return ResponseEntity.ok(service.getGenders());
    }

    @GetMapping("diabetes")
    public ResponseEntity diabetes(){
        return ResponseEntity.ok(service.getDiabetes());
    }

    @GetMapping("age")
    public ResponseEntity<List<AgeStatisticsModel>> age(){
        return ResponseEntity.ok(service.getAge());
    }
}
