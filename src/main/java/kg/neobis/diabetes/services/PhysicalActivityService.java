package kg.neobis.diabetes.services;


import kg.neobis.diabetes.entity.PhysicalActivity;
import kg.neobis.diabetes.repositories.PhysicalActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalActivityService {

    private final PhysicalActivityRepository repository;

    @Autowired
    PhysicalActivityService(PhysicalActivityRepository repository){
        this.repository = repository;
    }
}
