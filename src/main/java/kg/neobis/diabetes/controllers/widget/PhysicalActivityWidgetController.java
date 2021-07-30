package kg.neobis.diabetes.controllers.widget;

import kg.neobis.diabetes.models.widgets.physical_activity.TrackingActivityModel;
import kg.neobis.diabetes.services.PhysicalActivityWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("activity-widget")
public class PhysicalActivityWidgetController {

    private final PhysicalActivityWidgetService service;

    @Autowired
    public PhysicalActivityWidgetController(PhysicalActivityWidgetService service) {
        this.service = service;
    }

    @GetMapping("history")
    public ResponseEntity<?> getHistory(){
        return service.getHistory();
    }

    @PostMapping("track")
    public ResponseEntity<?> track(@RequestBody TrackingActivityModel model){
        return service.track(model);
    }


}
