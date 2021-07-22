package kg.neobis.diabetes.services;

import kg.neobis.diabetes.repositories.NormalUserPressureRepository;
import kg.neobis.diabetes.repositories.NormalUserSleepRepository;
import kg.neobis.diabetes.repositories.NormalUserSugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalUserPropertiesService {

    private final NormalUserPressureRepository pressureRepository;
    private final NormalUserSleepRepository sleepRepository;
    private final NormalUserSugarRepository sugarRepository;

    @Autowired
    public NormalUserPropertiesService(NormalUserPressureRepository pressureRepository, NormalUserSleepRepository sleepRepository, NormalUserSugarRepository sugarRepository) {
        this.pressureRepository = pressureRepository;
        this.sleepRepository = sleepRepository;
        this.sugarRepository = sugarRepository;
    }


}
