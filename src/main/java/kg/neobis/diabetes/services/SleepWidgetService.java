package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Sleep;
import kg.neobis.diabetes.models.MessageModel;
import kg.neobis.diabetes.models.widgets.sleep.SleepJournalModel;
import kg.neobis.diabetes.models.widgets.sleep.TrackingSleepModel;
import kg.neobis.diabetes.repositories.SleepRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return ResponseEntity.ok(covertToModel(repository.findAll()));
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
}
