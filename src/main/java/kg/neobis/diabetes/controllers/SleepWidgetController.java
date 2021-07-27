package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.widgets.sleep.TrackingSleepModel;
import kg.neobis.diabetes.services.SleepWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sleep-widget")
public class SleepWidgetController {

    private final SleepWidgetService service;

    @Autowired
    public SleepWidgetController(SleepWidgetService service) {
        this.service = service;
    }

    @PostMapping("track")
    public ResponseEntity<?> trackFood(@RequestBody TrackingSleepModel model)
    {
        return service.track(model);
    }
}
