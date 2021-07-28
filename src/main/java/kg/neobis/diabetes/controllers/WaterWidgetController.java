package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.widgets.water.TrackingWaterModel;
import kg.neobis.diabetes.services.WaterWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("water-widget")
public class WaterWidgetController {

    private final WaterWidgetService service;

    @Autowired
    public WaterWidgetController(WaterWidgetService service) {
        this.service = service;
    }

    @PostMapping("track")
    public ResponseEntity<?> trackWater(@RequestBody TrackingWaterModel model){
        try {
            return service.track(model);
        } catch (WrongDataException e){
            return new ResponseEntity<>(new MessageModel(e.getMessage()), e.getStatus());
        }
    }
}
