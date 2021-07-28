package kg.neobis.diabetes.services;

import kg.neobis.diabetes.repositories.SugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SugarWidgetService {

    private final SugarRepository repository;

    @Autowired
    public SugarWidgetService(SugarRepository repository) {
        this.repository = repository;
    }
}
