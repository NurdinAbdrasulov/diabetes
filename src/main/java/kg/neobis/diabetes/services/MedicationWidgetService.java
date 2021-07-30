package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.UserMedication;
import kg.neobis.diabetes.models.widgets.medication.MedicationJournalModel;
import kg.neobis.diabetes.models.widgets.medication.TrackingMedicationModel;
import kg.neobis.diabetes.repositories.UserMedicationRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MedicationWidgetService {

    private final UserMedicationRepository repository;
    private final MyUserServiceImpl userService;
    private final MedicationService medicationService;

    public MedicationWidgetService(UserMedicationRepository repository, MyUserServiceImpl userService, MedicationService medicationService) {
        this.repository = repository;
        this.userService = userService;
        this.medicationService = medicationService;
    }

    public ResponseEntity<?> track(TrackingMedicationModel model) {
        WidgetService.checkTime(model.getTime());

        UserMedication userMedication = new UserMedication();
        userMedication.setUser(userService.getCurrentUser());
        userMedication.setCreatedDate(new Date());
        userMedication.setTime(model.getTime());
        userMedication.setValue(model.getValue());
        userMedication.setMedication(medicationService.getEntityById(model.getMedicationId()));

        return  ResponseEntity.ok(convertToModel(repository.save(userMedication)));
    }

    private MedicationJournalModel convertToModel(UserMedication medication){
        MedicationJournalModel model = new MedicationJournalModel();
        model.setCreatedDate(medication.getCreatedDate());
        model.setTime(medication.getTime());
        model.setValue(medication.getValue());
        model.setMedication(medicationService.convertToModel(medication.getMedication()));
        return model;
    }

    private List<MedicationJournalModel> convertToModel(List<UserMedication> medications){
        List<MedicationJournalModel> list = new ArrayList<>();
        for(UserMedication medication : medications)
            list.add(convertToModel(medication));
        return list;
    }

    public ResponseEntity<?> getAll() {
        List<UserMedication> all = repository.findAll();
        return ResponseEntity.ok(convertToModel(all));
    }
}
