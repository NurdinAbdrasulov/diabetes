package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Food;
import kg.neobis.diabetes.entity.UserFood;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.widgets.food.TrackingFoodModel;
import kg.neobis.diabetes.repositories.UserFoodRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FoodWidgetService {

    private final FoodService foodService;
    private final UserFoodRepository repository;
    private final MyUserServiceImpl userService;

    @Autowired
    public FoodWidgetService(FoodService foodService, UserFoodRepository repository, MyUserServiceImpl userService) {
        this.foodService = foodService;
        this.repository = repository;
        this.userService = userService;
    }


    public ResponseEntity<?> track(TrackingFoodModel model) {
        
        for(Long foodId : model.getFoodIds()) {
            Food food = foodService.getEntityById(foodId);
            UserFood journal = new UserFood();
            journal.setTime(model.getTime());
            journal.setFood(food);
            journal.setUser(userService.getCurrentUser());
            repository.save(journal);
        }

        return ResponseEntity.ok(new MessageModel("added!"));
    }
}
