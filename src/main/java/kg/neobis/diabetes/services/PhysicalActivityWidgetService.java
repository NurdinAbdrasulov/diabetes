package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.PhysicalActivity;
import kg.neobis.diabetes.entity.UserPhysicalActivity;
import kg.neobis.diabetes.models.widgets.physical_activity.ActivityJournalModel;
import kg.neobis.diabetes.models.widgets.physical_activity.TrackingActivityModel;
import kg.neobis.diabetes.repositories.UserPhysicalActivityRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PhysicalActivityWidgetService {

    private final UserPhysicalActivityRepository repository;
    private final MyUserServiceImpl userService;
    private final PhysicalActivityService activityService;

    @Autowired
    public PhysicalActivityWidgetService(UserPhysicalActivityRepository repository, MyUserServiceImpl userService, PhysicalActivityService activityService) {
        this.repository = repository;
        this.userService = userService;
        this.activityService = activityService;
    }

    public ResponseEntity<?> track(TrackingActivityModel model) {
        WidgetService.checkTime(model.getTime());

        UserPhysicalActivity record = new UserPhysicalActivity();
        record.setTime(model.getTime());
        record.setUser(userService.getCurrentUser());
        record.setCreatedDate(new Date());
        record.setDuration(model.getDuration());
        record.setPhysicalActivity(activityService.getEntityById(model.getActivityId()));

        return ResponseEntity.ok(convertToModel(repository.save(record)));
    }

    private ActivityJournalModel convertToModel(UserPhysicalActivity activity){
        ActivityJournalModel model = new ActivityJournalModel();
        model.setDuration(activity.getDuration());
        model.setTime(activity.getTime());
        model.setCreatedDate(activity.getCreatedDate());
        model.setActivity(activityService.convertToModel(activity.getPhysicalActivity()));

        return model;
    }

    private List<ActivityJournalModel> convertToModel(List<UserPhysicalActivity> activities){
        List<ActivityJournalModel> list = new ArrayList<>();
        for(UserPhysicalActivity activity : activities)
            list.add(convertToModel(activity));
        return list;
    }

    public ResponseEntity<?> getHistory() {
        return ResponseEntity.ok(convertToModel(repository.findAll()));
    }

}


