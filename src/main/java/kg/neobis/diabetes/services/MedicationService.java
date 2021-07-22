package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Medication;
import kg.neobis.diabetes.models.MedicationModel;
import kg.neobis.diabetes.models.ModelToAddMedication;
import kg.neobis.diabetes.repositories.MedicationRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository repository;
    private final MyUserServiceImpl userService;

    @Autowired
    MedicationService(MedicationRepository repository, MyUserServiceImpl userService){
        this.repository = repository;
        this.userService = userService;
    }


    public List<MedicationModel>  creatMedication(List<ModelToAddMedication> models) {

        List<Medication> list = new ArrayList<>();
        for(var model: models) {
            Medication medication = new Medication();
            medication.setName(model.getName());
            medication.setUser(userService.getCurrentUser());
            list.add(repository.save(medication));
        }
        return convertToModel(list);
    }

    public MedicationModel  creatMedication(ModelToAddMedication model) {
        Medication medication = new Medication();
        medication.setName(model.getName());
        medication.setUser(userService.getCurrentUser());
        return convertToModel(medication);
    }

    public List<MedicationModel> convertToModel(List<Medication> medications){
        List<MedicationModel> models = new ArrayList<>();
        for(var medication : medications)
            models.add(new MedicationModel(medication.getId(), medication.getName(), medication.getUser().getId()));
        return models;
    }

    public MedicationModel convertToModel(Medication medication){
        return new MedicationModel(medication.getId(), medication.getName(), medication.getUser().getId());
    }
}
