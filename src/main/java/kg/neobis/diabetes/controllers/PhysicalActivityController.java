package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.ModelToCreatPhysicalActivity;
import kg.neobis.diabetes.models.PhysicalActivityModel;
import kg.neobis.diabetes.services.PhysicalActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("activities")
public class PhysicalActivityController {

    private final PhysicalActivityService service;

    @Autowired
    PhysicalActivityController(PhysicalActivityService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam String name,
                                                     @RequestParam MultipartFile icon){
        try {
            return ResponseEntity.ok(service.create(name, icon));
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("test")
//    public ResponseEntity<?> createTest(@RequestParam String name,
//                                                        @RequestParam MultipartFile icon) throws IOException {
//        return ResponseEntity.ok(service.createTest(name, icon));
//    }
//    @GetMapping("test/{id}")
//    public ResponseEntity<?> get(@PathVariable Long id) throws IOException {
//        return ResponseEntity.ok(service.testGetByID(id));
//    }

    @GetMapping
    public ResponseEntity<List<PhysicalActivityModel>> getAll(){

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return  service.getById(id);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam MultipartFile icon) {
        try {
            return ResponseEntity.ok(service.update(id, name, icon));
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch ( IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(new MessageModel("скоро будет реализовано)"));
    }


    }
