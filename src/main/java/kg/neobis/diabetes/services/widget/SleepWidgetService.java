package kg.neobis.diabetes.services.widget;

import kg.neobis.diabetes.entity.Sleep;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.main_page.SleepMainPageModel;
import kg.neobis.diabetes.models.widgets.sleep.SleepJournalModel;
import kg.neobis.diabetes.models.widgets.sleep.TrackingSleepModel;
import kg.neobis.diabetes.repositories.SleepRepository;
import kg.neobis.diabetes.services.WidgetService;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SleepWidgetService {

    private final SleepRepository repository;
    private final MyUserServiceImpl userService;

    @Autowired
    public SleepWidgetService(SleepRepository repository, MyUserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<?> track(TrackingSleepModel model) {

        WidgetService.checkTime(model.getEndTime());
        WidgetService.checkTime(model.getStartTime());

        Sleep sleep = new Sleep();
        sleep.setUser(userService.getCurrentUser());
        sleep.setStartTime(model.getStartTime());
        sleep.setEndTime(model.getEndTime());

        return ResponseEntity.ok(convertToModel(repository.save(sleep)));
    }

    public ResponseEntity<?> getHistory() {
        return ResponseEntity.ok(covertToModel(repository.findAllByUser(userService.getCurrentUser())));
    }

    private List<SleepJournalModel> covertToModel(List<Sleep> all) {
        List<SleepJournalModel> list = new ArrayList<>();
        for(Sleep sleep : all)
            list.add(convertToModel(sleep));

        return list;
    }

    private SleepJournalModel convertToModel(Sleep sleep){
        SleepJournalModel model = new SleepJournalModel(sleep.getStartTime(), sleep.getEndTime(), sleep.getCreatedDate());
        return model;
    }


    public SleepMainPageModel getInfoForMainPage(){

        Optional<Sleep> optionalSleep = repository.findFirstByOrderByCreatedDateDesc();
        if(optionalSleep.isEmpty())
            return null;

        Sleep sleep = optionalSleep.get();
        SleepMainPageModel sleepMainPageModel = new SleepMainPageModel();
        sleepMainPageModel.setEndTime(sleep.getEndTime());
        sleepMainPageModel.setStartTime(sleep.getStartTime());
        sleepMainPageModel.setDuration(getHourMinuter(sleep.getStartTime(), sleep.getEndTime()));

        return sleepMainPageModel;
    }


    /**
     * Пользователь не может спать больше 24 часов
     * @param start время когда уснул
     * @param end время пробуждения
     * @return длительность сна
     */
    private String getHourMinuter(String start, String end){
        int startHour = Integer.parseInt(start.split(":")[0]);
        int startMinutes = Integer.parseInt(start.split(":")[1]);
        int endHour = Integer.parseInt(end.split(":")[0]);
        int endMinutes = Integer.parseInt(end.split(":")[1]);

        int hour = endHour > startHour ? endHour - startHour : 24 - startHour + endHour;// что если пользователь уснул и проснулся в тот же час

        int minute = endMinutes > startMinutes ? endMinutes - startMinutes : 60 - startMinutes + endMinutes;
        if(minute == 60) minute = 0;
        if(endMinutes < startMinutes)
            hour--;

        return hour + ":" + minute;
    }
}
