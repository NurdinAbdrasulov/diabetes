package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Insulin;
import kg.neobis.diabetes.models.widgets.insulin.InsulinJournalModel;
import kg.neobis.diabetes.models.widgets.insulin.TrackingInsulinModel;
import kg.neobis.diabetes.repositories.InsulinRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
