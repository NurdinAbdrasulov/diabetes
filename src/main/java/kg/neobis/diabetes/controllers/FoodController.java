package kg.neobis.diabetes.controllers;

import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import javassist.NotFoundException;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
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

    @GetMapping("filter")
    public ResponseEntity<?> getFiltered(@RequestParam(value = "category-id", required = false) Long categoryID, @RequestParam(value = "is-my-food", required = false, defaultValue = "false") Boolean isMyFood){
        try {
            return ResponseEntity.ok(service.getFiltered(categoryID, isMyFood));
        } catch (WrongDataException | RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
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
