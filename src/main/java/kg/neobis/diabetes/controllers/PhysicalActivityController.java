package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.models.ModelToCreatPhysicalActivity;
import kg.neobis.diabetes.models.PhysicalActivityModel;
import kg.neobis.diabetes.services.PhysicalActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("activity")
public class PhysicalActivityController {

    private final PhysicalActivityService service;

    @Autowired
    PhysicalActivityController(PhysicalActivityService service){
        this.service = service;
    }

    @PostMapping("add")
    public ResponseEntity<PhysicalActivityModel> fun(@RequestParam String name,
                                                     @RequestParam MultipartFile icon){
            return ResponseEntity.ok(service.create(name, icon));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<PhysicalActivityModel>> getAll(){

        return ResponseEntity.ok(service.getAll());
    }


    @PutMapping("update")
    public ResponseEntity update(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam MultipartFile icon) {
        try {
            return ResponseEntity.ok(service.update(id, name, icon));
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    }
