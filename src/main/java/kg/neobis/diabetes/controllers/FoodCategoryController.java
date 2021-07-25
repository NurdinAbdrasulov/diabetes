package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.entity.FoodCategory;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.FoodCategoryModel;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.ModelToAddFoodCategory;
import kg.neobis.diabetes.services.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food-categories")
public class FoodCategoryController {

    private final FoodCategoryService service;

    @Autowired
    public FoodCategoryController(FoodCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FoodCategoryModel>> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return service.getById(id);
        } catch (RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }

    @PostMapping
    private ResponseEntity<FoodCategoryModel> createCategory(@RequestBody ModelToAddFoodCategory model){
        return service.create(model);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody ModelToAddFoodCategory model, @PathVariable Long id){
        try {
            return service.updateById(model, id);
        } catch (RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            service.deleteById(id);
            return ResponseEntity.ok(new MessageModel("successfully deleted"));
        } catch (RecordNotFoundException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }
}
