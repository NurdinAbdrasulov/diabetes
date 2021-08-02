package kg.neobis.diabetes.controllers.widget;

import kg.neobis.diabetes.models.widgets.sleep.TrackingSleepModel;
import kg.neobis.diabetes.services.widget.SleepWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sleep-widget")
public class SleepWidgetController {

    private final SleepWidgetService service;

    @Autowired
    public SleepWidgetController(SleepWidgetService service) {
        this.service = service;
    }

    @GetMapping("history")
    public ResponseEntity<?> getHistory(){
        return service.getHistory();
    }

    @PostMapping("track")
    public ResponseEntity<?> trackFood(@RequestBody TrackingSleepModel model)
    {
        return service.track(model);
    }
}
