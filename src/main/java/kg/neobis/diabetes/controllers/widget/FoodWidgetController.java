package kg.neobis.diabetes.controllers.widget;

import kg.neobis.diabetes.models.widgets.food.TrackingFoodModel;
import kg.neobis.diabetes.services.widget.FoodWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food-widget")
public class FoodWidgetController {

    private final FoodWidgetService service;

    @Autowired
    public FoodWidgetController(FoodWidgetService service) {
        this.service = service;
    }

    @GetMapping("history")
    public ResponseEntity<?> getAll(){
        return service.getHistory();
    }

    @PostMapping("track")
    public ResponseEntity<?> trackFood(@RequestBody TrackingFoodModel model)
    {
        return service.track(model);
    }

}
