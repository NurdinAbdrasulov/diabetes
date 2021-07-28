package kg.neobis.diabetes.services;

import kg.neobis.diabetes.repositories.InsulinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsulinWidgetService {

    private final InsulinRepository repository;


    @Autowired
    public InsulinWidgetService(InsulinRepository repository) {
        this.repository = repository;
    }
}
