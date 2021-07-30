package kg.neobis.diabetes.controllers;

import kg.neobis.diabetes.models.MedicationModel;
import kg.neobis.diabetes.models.ModelToAddMedications;
import kg.neobis.diabetes.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<MedicationModel>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }



}
