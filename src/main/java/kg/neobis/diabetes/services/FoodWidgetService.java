package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Food;
import kg.neobis.diabetes.entity.UserFoodJournal;
import kg.neobis.diabetes.models.widgets.food.FoodJournalModel;
import kg.neobis.diabetes.models.widgets.food.TrackingFoodModel;
import kg.neobis.diabetes.repositories.UserFoodRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        WidgetService.checkTime(model.getTime());

        Set<Food> foodSet = new HashSet<>();

        for(Long foodId : model.getFoodIds())
            foodSet.add(foodService.getEntityById(foodId));

        UserFoodJournal journal = new UserFoodJournal();
        journal.setTime(model.getTime());
        journal.setFood(foodSet);
        journal.setUser(userService.getCurrentUser());


        return ResponseEntity.ok(convertToModel(repository.save(journal)));
    }

    private FoodJournalModel convertToModel(UserFoodJournal userFood){
        FoodJournalModel model = new FoodJournalModel();
        model.setTime(userFood.getTime());
        model.setCreatedDate(userFood.getCreatedDate());
        model.setFood(foodService.convertToFoodModel(userFood.getFood()));

        return model;
    }

    private List<FoodJournalModel> convertToModel(List<UserFoodJournal> list){
        List<FoodJournalModel> resultList = new ArrayList<>();
        for(UserFoodJournal record : list)
            resultList.add(convertToModel(record));
        return resultList;
    }

    public ResponseEntity<?> getAll() {
        List<UserFoodJournal> all = repository.findAll();
        return ResponseEntity.ok(convertToModel(all));
    }
}
