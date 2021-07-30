package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Medication;
import kg.neobis.diabetes.models.MedicationModel;
import kg.neobis.diabetes.models.ModelToAddMedications;
import kg.neobis.diabetes.repositories.MedicationRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepository repository;
    private final MyUserServiceImpl userService;

    @Autowired
    MedicationService(MedicationRepository repository, MyUserServiceImpl userService){
        this.repository = repository;
        this.userService = userService;
    }



    public List<MedicationModel>  creatMedication(ModelToAddMedications model) {
        List<Medication> medications = new ArrayList<>();
        for(String medName : model.getNames()){
            Medication medication = new Medication();
            medication.setName(medName);
            medication.setUser(userService.getCurrentUser());
            medications.add(repository.save(medication));
        }

        return convertToModel(medications);
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

    public List<MedicationModel> getAll() {
        List<Medication> all = repository.findAll();
        return convertToModel(all);
    }

    public Medication getEntityById(Long medicationId) {
        Optional<Medication> byId = repository.findById(medicationId);

        if(byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "нет медикомента с id " + medicationId);

        return byId.get();
    }
}
