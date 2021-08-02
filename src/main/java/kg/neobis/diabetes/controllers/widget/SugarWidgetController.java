package kg.neobis.diabetes.controllers.widget;


import kg.neobis.diabetes.models.widgets.sugar.SugarJournalModel;
import kg.neobis.diabetes.models.widgets.sugar.TrackingSugarModel;
import kg.neobis.diabetes.services.widget.SugarWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sugar-widget")
public class SugarWidgetController {

    private final SugarWidgetService service;

    @Autowired
    public SugarWidgetController(SugarWidgetService service) {
        this.service = service;
    }

    @GetMapping("history")
    public ResponseEntity<List<SugarJournalModel>> getHistory(){
        return service.getHistory();
    }

    @PostMapping("track")
    public ResponseEntity<SugarJournalModel> track(@RequestBody TrackingSugarModel model){
        return service.track(model);
    }
}
