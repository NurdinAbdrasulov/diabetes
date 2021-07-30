package kg.neobis.diabetes.controllers.widget;

import kg.neobis.diabetes.models.widgets.pressure.PressureJournalModel;
import kg.neobis.diabetes.models.widgets.pressure.TrackingPressureModel;
import kg.neobis.diabetes.services.PressureWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pressure-widget")
public class PressureWidgetController {

    private final PressureWidgetService service;

    @Autowired
    public PressureWidgetController(PressureWidgetService service) {
        this.service = service;
    }

    @GetMapping("history")
    public ResponseEntity<?> getHistory(){
        return service.getHistory();
    }

    @PostMapping("track")
    public ResponseEntity<PressureJournalModel> track(@RequestBody TrackingPressureModel model){
        return service.track(model);
    }
}
