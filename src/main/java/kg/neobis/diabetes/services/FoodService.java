package kg.neobis.diabetes.services;

import javassist.NotFoundException;
import kg.neobis.diabetes.entity.Food;
import kg.neobis.diabetes.entity.FoodCategory;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.models.FoodModel;
import kg.neobis.diabetes.models.ModelToAddFood;
import kg.neobis.diabetes.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository repository;

    @Autowired
    private  FoodCategoryService foodCategoryService;

    @Autowired
    FoodService(FoodRepository repository){
        this.repository = repository;
    }


    public List<FoodModel> getAll() {
        List<Food> all = repository.findAllByUserIsNull();
        List<FoodModel> resultList = new ArrayList<>();

        for(Food food: all){
            FoodModel model = convertToFoodModel(food);
            resultList.add(model);
        }
        return resultList;
    }

    public FoodModel add(ModelToAddFood model) throws RecordNotFoundException{
        Food food = new Food();
        food.setName(model.getName());
        food.setProteins(model.getProteins());
        food.setFats(model.getFats());
        food.setCarbohydrates(model.getCarbohydrates());
        food.setCalories(model.getCalories());///////////
        FoodCategory category = foodCategoryService.getRealCategory(model.getCategoryId());
        food.setCategory(category);

        return convertToFoodModel(repository.save(food));
    }


    private FoodModel convertToFoodModel(Food food){
        FoodModel model = new FoodModel();
        model.setId(food.getId());
        model.setName(food.getName());
        model.setProteins(food.getProteins());
        model.setFats(food.getFats());
        model.setCarbohydrates(food.getCarbohydrates());
        model.setCalories(food.getCalories());

        if(food.getUser() == null && food.getCategory() != null)
            model.setCategory(foodCategoryService.convertToModel(food.getCategory()));
        return model;
    }

    public FoodModel update( Long id, ModelToAddFood model) throws RecordNotFoundException {
        Optional<Food> optionalFood = repository.findById(id);
        if(optionalFood.isEmpty())
            throw new RecordNotFoundException("нет продукта с id "  + id);

        Food food = optionalFood.get();
        food.setName(model.getName());
        food.setProteins(model.getProteins());
        food.setFats(model.getFats());
        food.setCarbohydrates(model.getCarbohydrates());
        food.setCalories(model.getCalories());

        if(model.getCategoryId() != null) {
            FoodCategory category = foodCategoryService.getRealCategory(model.getCategoryId());
            food.setCategory(category);
        }


        return convertToFoodModel(repository.save(food));
    }

    public void deleteCategory(FoodCategory category){
        List<Food> byCategory = repository.findByCategory(category);
        for( Food food: byCategory){
            food.setCategory(null);
            repository.save(food);
        }
    }

    public ResponseEntity<?> getById(Long id) {

        Optional<Food> byId = repository.findById(id);
        if(byId.isEmpty())
            throw new RecordNotFoundException("нет еды с id " + id);

        return ResponseEntity.ok(convertToFoodModel(byId.get()));

    }

    // TO DO
    public Object delete(Long id) {
        Optional<Food> byId = repository.findById(id);
        if(byId.isEmpty())
            throw new RecordNotFoundException("нет еды с id " + id);

        return ResponseEntity.ok("Типа удалено)");

    }
}
