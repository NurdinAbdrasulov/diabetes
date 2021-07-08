package kg.neobis.diabetes.services;

import javassist.NotFoundException;
import kg.neobis.diabetes.entity.Food;
import kg.neobis.diabetes.models.FoodModel;
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
    FoodService(FoodRepository repository){
        this.repository = repository;
    }


    public List<FoodModel> getAll() {
        List<Food> all = repository.findAll();
        List<FoodModel> resultList = new ArrayList<>();

        for(Food food: all){
            FoodModel model = convertToFoodModel(food);
            resultList.add(model);
        }
        return resultList;
    }

    public FoodModel add(FoodModel model) {
        Food food = new Food();
        food.setName(model.getName());
        food.setProteins(model.getProteins());
        food.setFats(model.getFats());
        food.setCarbohydrates(model.getCarbohydrates());
        food.setCalories(model.getCalories());///////////

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
        return model;
    }

    public FoodModel update(FoodModel model) throws NotFoundException {
        Optional<Food> optionalFood = repository.findById(model.getId());
        if(optionalFood.isEmpty())
            throw new NotFoundException("нет продукта с id "  + model.getId());

        Food food = optionalFood.get();
        food.setName(model.getName());
        food.setProteins(model.getProteins());
        food.setFats(model.getFats());
        food.setCarbohydrates(model.getCarbohydrates());
        food.setCalories(model.getCalories());///////////


        return convertToFoodModel(repository.save(food));
    }
}
