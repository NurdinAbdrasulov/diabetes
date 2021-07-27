package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Sleep;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.widgets.food.TrackingFoodModel;
import kg.neobis.diabetes.models.widgets.sleep.TrackingSleepModel;
import kg.neobis.diabetes.repositories.SleepRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SleepWidgetService {

    private final SleepRepository repository;
    private final MyUserServiceImpl userService;

    @Autowired
    public SleepWidgetService(SleepRepository repository, MyUserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<?> track(TrackingSleepModel model) {

        Sleep sleep = new Sleep();
        sleep.setUser(userService.getCurrentUser());
        sleep.setStartTime(model.getStartTime());
        sleep.setEndTime(model.getEndTime());
        repository.save(sleep);
        return ResponseEntity.ok(new MessageModel("ok"));
    }
}
