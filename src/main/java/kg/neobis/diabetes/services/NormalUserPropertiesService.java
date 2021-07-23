package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.NormalUserPressure;
import kg.neobis.diabetes.entity.NormalUserSleep;
import kg.neobis.diabetes.entity.NormalUserSugar;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.ModelToAddNormalUserPressure;
import kg.neobis.diabetes.models.ModelToAddNormalUserSleep;
import kg.neobis.diabetes.models.ModelToAddNormalUserSugar;
import kg.neobis.diabetes.repositories.NormalUserPressureRepository;
import kg.neobis.diabetes.repositories.NormalUserSleepRepository;
import kg.neobis.diabetes.repositories.NormalUserSugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public void setSleep(ModelToAddNormalUserSleep model, User user) {
        if (!isTime(model.getEndTime()) || !isTime(model.getStartTime()))
            throw new WrongDataException("время должно быть в формате HH:mm");

        Optional<NormalUserSleep> byUser = sleepRepository.findByUser(user);

        NormalUserSleep userSleep = byUser.orElseGet(NormalUserSleep::new);
        userSleep.setStartTime(model.getStartTime());
        userSleep.setEndTime(model.getEndTime());
        userSleep.setUser(user);

        sleepRepository.save(userSleep);
    }

    private boolean isTime(String value){
        return value.matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
    }

    public void setPressure(ModelToAddNormalUserPressure model, User user) {

        Optional<NormalUserPressure> byUser = pressureRepository.findByUser(user);

        NormalUserPressure pressure = byUser.orElseGet(NormalUserPressure::new);
        pressure.setUser(user);
        pressure.setDiastolic(model.getDiastolic());
        pressure.setSystolic(model.getSystolic());

        pressureRepository.save(pressure);

    }

    public void setSugar(ModelToAddNormalUserSugar model, User user) {
        Optional<NormalUserSugar> byUser = sugarRepository.findByUser(user);

        NormalUserSugar sugar = byUser.orElseGet(NormalUserSugar::new);
        sugar.setUser(user);
        sugar.setValue(model.getValue());
        sugarRepository.save(sugar);
    }
}
