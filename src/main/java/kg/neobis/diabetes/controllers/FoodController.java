package kg.neobis.diabetes.controllers;

import javassist.NotFoundException;
import kg.neobis.diabetes.models.FoodModel;
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

    @GetMapping("getAll")
    public ResponseEntity<List<FoodModel>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody FoodModel model){
        try {
            return ResponseEntity.ok(service.update(model));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // добавить валидации: продукт уже сущ, пустыне поля и тд
    @PostMapping("add")
    public ResponseEntity<FoodModel> add(@RequestBody FoodModel model){
        return ResponseEntity.ok(service.add(model));
    }

}
