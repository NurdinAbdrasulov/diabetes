package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.ModelToAddMedications;
import kg.neobis.diabetes.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medications")
public class MedicationController {

    private final MedicationService service;

    @Autowired
    MedicationController(MedicationService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> newMedications(@RequestBody ModelToAddMedications model){
        return ResponseEntity.ok(service.creatMedication(model));
    }


}
