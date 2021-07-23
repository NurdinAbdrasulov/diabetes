package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.NormalUserSleep;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.models.ModelToAddNormalUserSleep;
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


    public void setUserSleep(ModelToAddNormalUserSleep model, User user) {
        NormalUserSleep userSleep = new NormalUserSleep();
        userSleep.setStartTime(model.getStartTime());
        userSleep.setEndTime(model.getEndTime());
        userSleep.setUser(user);

        sleepRepository.save(userSleep);
    }
}
