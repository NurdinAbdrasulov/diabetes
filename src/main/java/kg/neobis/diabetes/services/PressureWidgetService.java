package kg.neobis.diabetes.services;

import kg.neobis.diabetes.repositories.PressureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PressureWidgetService {

    private final PressureRepository repository;

    @Autowired
    public PressureWidgetService(PressureRepository repository) {
        this.repository = repository;
    }
}
