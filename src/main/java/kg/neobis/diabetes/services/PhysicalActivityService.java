package kg.neobis.diabetes.services;


import kg.neobis.diabetes.entity.PhysicalActivity;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.PhysicalActivityModel;
import kg.neobis.diabetes.repositories.PhysicalActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class PhysicalActivityService {

    private final PhysicalActivityRepository repository;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${server-address}")
    private String ip;

    @Autowired
    PhysicalActivityService(PhysicalActivityRepository repository){
        this.repository = repository;
    }

    public PhysicalActivityModel create(String name, MultipartFile icon) throws IOException {

        PhysicalActivity activity = new PhysicalActivity();
        activity.setName(name);
        activity.setImg_ur(uploadImage(icon));
        return convertToModel(repository.save(activity));
    }


    public List<PhysicalActivityModel> getAll(){
        List<PhysicalActivity> all = repository.findAll();
        List<PhysicalActivityModel> modelList = new ArrayList<>();

        for(PhysicalActivity activity : all)
            modelList.add(convertToModel(activity));

        return modelList;

    }


    public PhysicalActivityModel convertToModel(PhysicalActivity activity){
        PhysicalActivityModel model = new PhysicalActivityModel();
        model.setId(activity.getId());
        model.setName(activity.getName());
        model.setIcon(ip + "/img/" + activity.getImg_ur());
        return model;
    }


    private  String encodeFileToBase64(MultipartFile file) throws IllegalStateException{
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    public PhysicalActivityModel update(Long id, String name, MultipartFile icon) throws RecordNotFoundException, IOException {
        Optional<PhysicalActivity> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new RecordNotFoundException("нет физ активности с id " + id);

        PhysicalActivity activity = byId.get();
        String oldFile = activity.getImg_ur();
        activity.setName(name);
        activity.setImg_ur(uploadImage(icon));

        File file = new File(uploadPath + File.separator + oldFile);
        file.delete();

        return convertToModel(repository.save(activity));


    }

    public ResponseEntity<?> getById(Long id) {
        Optional<PhysicalActivity> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"нет физ активности с id " + id);

        return ResponseEntity.ok(convertToModel(byId.get()));
    }



    //returns saved Image name
    private String uploadImage( MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            try {
                File val = new File(uploadPath + File.separator + resultFilename);
                val.createNewFile();
                file.transferTo(val);
                return resultFilename;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        throw new WrongDataException("файл без имени или пустой файл");

    }

    public PhysicalActivity getEntityById(Long activityId) {
        Optional<PhysicalActivity> byId = repository.findById(activityId);

        if(byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"нет физ активности с id " + activityId);

        return byId.get();
    }

/*    public TestModel testGetByID(Long id) throws IOException {
        Optional<PhysicalActivity> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new RecordNotFoundException("нет иконки с id");

        PhysicalActivity activity = byId.get();

        TestModel model = new TestModel();
        model.setId(activity.getId());
        model.setName(activity.getName());

        File file = new File(uploadPath + File.separator + activity.getImg_ur());
        model.setIcon(file);
        return model;
    }*/
}
