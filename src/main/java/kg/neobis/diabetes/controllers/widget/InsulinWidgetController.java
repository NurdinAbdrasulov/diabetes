package kg.neobis.diabetes.controllers.widget;

import kg.neobis.diabetes.models.widgets.insulin.TrackingInsulinModel;
import kg.neobis.diabetes.services.widget.InsulinWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("insulin-widget")
public class InsulinWidgetController {

    private final InsulinWidgetService service;

    @Autowired
    public InsulinWidgetController(InsulinWidgetService service) {
        this.service = service;
    }

    @PostMapping("track")
    public ResponseEntity<?> track(@RequestBody TrackingInsulinModel model){
        return service.track(model);
    }

    @GetMapping("history")
    public ResponseEntity<?> getHistory(){
        return service.getHistory();
    }
}
