package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.widgets.medication.TrackingMedicationModel;
import kg.neobis.diabetes.services.MedicationWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medication-widget")
public class MedicationWidgetController {

    private final MedicationWidgetService service;

    @Autowired
    public MedicationWidgetController(MedicationWidgetService service) {
        this.service = service;
    }


    @PostMapping("track")
    public ResponseEntity<?> track(TrackingMedicationModel model){
        return  null;
    }
}
