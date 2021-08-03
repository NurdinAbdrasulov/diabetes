package kg.neobis.diabetes.services.widget;

import kg.neobis.diabetes.entity.Insulin;
import kg.neobis.diabetes.entity.Sugar;
import kg.neobis.diabetes.models.main_page.InsulinMainPageModel;
import kg.neobis.diabetes.models.main_page.SugarMainPageModel;
import kg.neobis.diabetes.models.widgets.insulin.InsulinJournalModel;
import kg.neobis.diabetes.models.widgets.insulin.TrackingInsulinModel;
import kg.neobis.diabetes.repositories.InsulinRepository;
import kg.neobis.diabetes.services.WidgetService;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InsulinWidgetService {

    private final InsulinRepository repository;
    private final MyUserServiceImpl userService;


    @Autowired
    public InsulinWidgetService(InsulinRepository repository, MyUserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<?> track(TrackingInsulinModel model) {
        WidgetService.checkTime(model.getTime());
        Insulin insulin = new Insulin(0l, userService.getCurrentUser(), model.getValue(), model.getTime(),new Date());

        return ResponseEntity.ok(convertToModel(repository.save(insulin)));
    }

    private InsulinJournalModel convertToModel(Insulin insulin){
        return new InsulinJournalModel(insulin.getTime(), insulin.getCreatedDate(), insulin.getValue());
    }

    private List<InsulinJournalModel> convertToModel(List<Insulin> list){
        List<InsulinJournalModel> modelList = new ArrayList<>();
        for(Insulin insulin: list)
            modelList.add(convertToModel(insulin));
        return modelList;
    }

    public ResponseEntity<List<InsulinJournalModel>> getAll(){
        List<Insulin> all = repository.findAll();
        return ResponseEntity.ok(convertToModel(all));
    }

    public ResponseEntity<List<InsulinJournalModel>> getHistory(){
        List<Insulin> all = repository.findAllByUser(userService.getCurrentUser());
        return ResponseEntity.ok(convertToModel(all));
    }

    public InsulinMainPageModel getForMainPage() {
        Optional<Insulin> optionalInsulin = repository.findFirstByUserOrderByCreatedDateDesc(userService.getCurrentUser());
        if(optionalInsulin.isEmpty())
        return null;

        Insulin insulin = optionalInsulin.get();

//        if(!WidgetService.isToday(insulin.getCreatedDate()))
//            return null;

        InsulinMainPageModel model = new InsulinMainPageModel();
        model.setValue(insulin.getValue());
        model.setTrackedTime(insulin.getCreatedDate());

        return  model;
    }
}
