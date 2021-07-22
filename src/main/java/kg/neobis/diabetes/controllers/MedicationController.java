package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.MedicationModel;
import kg.neobis.diabetes.models.ModelToAddMedication;
import kg.neobis.diabetes.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("medication")
public class MedicationController {

    private final MedicationService service;

    @Autowired
    MedicationController(MedicationService service){
        this.service = service;
    }

    @PostMapping("create/list")
    public ResponseEntity<?> newMedications(@RequestBody List<ModelToAddMedication> models){
        return ResponseEntity.ok(service.creatMedication(models));
    }

    @PostMapping("create/one")
    public ResponseEntity<?> newMedication(@RequestBody ModelToAddMedication model){
        return ResponseEntity.ok(service.creatMedication(model));
    }


}
