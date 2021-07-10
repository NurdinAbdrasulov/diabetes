package kg.neobis.diabetes.services;


import kg.neobis.diabetes.entity.PhysicalActivity;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.models.ModelToCreatPhysicalActivity;
import kg.neobis.diabetes.models.PhysicalActivityModel;
import kg.neobis.diabetes.repositories.PhysicalActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalActivityService {

    private final PhysicalActivityRepository repository;

    @Autowired
    PhysicalActivityService(PhysicalActivityRepository repository){
        this.repository = repository;
    }

    public PhysicalActivityModel create(String name, MultipartFile icon) {

        String base64 = encodeFileToBase64(icon);

        PhysicalActivity activity = new PhysicalActivity();
        activity.setName(name);
        activity.setIcon(base64);
        return convertToModel(repository.save(activity));
    }


    public List<PhysicalActivityModel> getAll(){
        List<PhysicalActivity> all = repository.findAll();
        List<PhysicalActivityModel> modelList = new ArrayList<>();

        for(PhysicalActivity activity : all)
            modelList.add(convertToModel(activity));

        return modelList;

    }


    private PhysicalActivityModel convertToModel(PhysicalActivity activity){
        PhysicalActivityModel model = new PhysicalActivityModel();
        model.setId(activity.getId());
        model.setName(activity.getName());
        model.setIcon("data:image/png;base64," + activity.getIcon());
        return model;
    }







    private  String encodeFileToBase64(MultipartFile file) {
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    public PhysicalActivityModel update(Long id, String name, MultipartFile icon) throws RecordNotFoundException {
        Optional<PhysicalActivity> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new RecordNotFoundException("нет физ активности с id " + id);

        PhysicalActivity activity = byId.get();
        activity.setName(name);
        activity.setIcon(encodeFileToBase64(icon));
        return convertToModel(repository.save(activity));


    }
}
