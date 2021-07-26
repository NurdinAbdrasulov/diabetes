package kg.neobis.diabetes.controllers;

import javassist.NotFoundException;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.models.FoodModel;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.ModelToAddFood;
import kg.neobis.diabetes.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
@CrossOrigin
public class FoodController {

    private final FoodService service;

    @Autowired
    FoodController(FoodService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FoodModel>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return service.getById(id);
        } catch (RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ModelToAddFood model){
        try {
            return ResponseEntity.ok(service.update(id, model));
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    // добавить валидации: продукт уже сущ, пустыне поля и тд
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ModelToAddFood model){
        try {
            return ResponseEntity.ok(service.add(model));
        } catch (RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }

    // TO DO
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }

}
