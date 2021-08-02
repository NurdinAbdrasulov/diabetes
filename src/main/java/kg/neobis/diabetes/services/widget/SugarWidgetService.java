package kg.neobis.diabetes.services.widget;

import kg.neobis.diabetes.entity.Sugar;
import kg.neobis.diabetes.models.main_page.SugarMainPageModel;
import kg.neobis.diabetes.models.widgets.sugar.SugarJournalModel;
import kg.neobis.diabetes.models.widgets.sugar.TrackingSugarModel;
import kg.neobis.diabetes.repositories.SugarRepository;
import kg.neobis.diabetes.services.NormalUserPropertiesService;
import kg.neobis.diabetes.services.WidgetService;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SugarWidgetService {

    private final SugarRepository repository;
    private final MyUserServiceImpl userService;
    private final NormalUserPropertiesService normalService;

    @Autowired
    public SugarWidgetService(SugarRepository repository, MyUserServiceImpl userService, NormalUserPropertiesService normalService) {
        this.repository = repository;
        this.userService = userService;
        this.normalService = normalService;
    }

    public ResponseEntity<SugarJournalModel> track(TrackingSugarModel model) {
        WidgetService.checkTime(model.getTime());

        Sugar sugar = new Sugar();
        sugar.setCreatedDate(new Date());
        sugar.setTime(model.getTime());
        sugar.setValue(model.getValue());
        sugar.setUser(userService.getCurrentUser());
        return  ResponseEntity.ok(convertToModel(repository.save(sugar)));

    }

    private SugarJournalModel convertToModel(Sugar sugar){
        Double normalSugarValue = normalService.getNormalSugarValue(userService.getCurrentUser());
        Boolean isNormal = normalSugarValue - 0.5 < sugar.getValue() && sugar.getValue() < normalSugarValue + 0.5;
        return new SugarJournalModel(sugar.getValue(), sugar.getTime(),sugar.getCreatedDate(), isNormal);
    }

    private List<SugarJournalModel> convertToModel(List<Sugar> sugars) {
        List<SugarJournalModel> list = new ArrayList<>();
        for(Sugar sugar : sugars)
            list.add(convertToModel(sugar));
        return list;

    }


    public ResponseEntity<List<SugarJournalModel>> getHistory() {
        return  ResponseEntity.ok(convertToModel(repository.findAllByUser(userService.getCurrentUser())));
    }

    public SugarMainPageModel getForMainPage() {

        return null;
    }
}
