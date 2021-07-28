package kg.neobis.diabetes.services;

import kg.neobis.diabetes.repositories.UserMedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationWidgetService {

    private final UserMedicationRepository repository;

    public MedicationWidgetService(UserMedicationRepository repository) {
        this.repository = repository;
    }
}
