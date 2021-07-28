package kg.neobis.diabetes.services;

import kg.neobis.diabetes.repositories.UserPhysicalActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalActivityWidgetService {

    private final UserPhysicalActivityRepository repository;

    @Autowired
    public PhysicalActivityWidgetService(UserPhysicalActivityRepository repository) {
        this.repository = repository;
    }
}
