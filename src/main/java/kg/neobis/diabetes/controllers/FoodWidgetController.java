package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.widgets.food.TrackingFoodModel;
import kg.neobis.diabetes.services.FoodWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("food-widget")
public class FoodWidgetController {

    private final FoodWidgetService service;

    @Autowired
    public FoodWidgetController(FoodWidgetService service) {
        this.service = service;
    }


    @PostMapping("track")
    public ResponseEntity<?> trackFood(@RequestBody TrackingFoodModel model)
    {
        return service.track(model);
    }




}
