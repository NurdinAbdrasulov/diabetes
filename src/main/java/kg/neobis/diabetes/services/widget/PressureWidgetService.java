package kg.neobis.diabetes.services.widget;

import kg.neobis.diabetes.entity.Pressure;
import kg.neobis.diabetes.models.main_page.PressureMainPageModel;
import kg.neobis.diabetes.models.widgets.pressure.PressureJournalModel;
import kg.neobis.diabetes.models.widgets.pressure.TrackingPressureModel;
import kg.neobis.diabetes.repositories.PressureRepository;
import kg.neobis.diabetes.services.NormalUserPropertiesService;
import kg.neobis.diabetes.services.WidgetService;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PressureWidgetService {

    private final PressureRepository repository;
    private final MyUserServiceImpl userService;
    private final NormalUserPropertiesService normalService;

    @Autowired
    public PressureWidgetService(PressureRepository repository, MyUserServiceImpl userService, NormalUserPropertiesService normalService) {
        this.repository = repository;
        this.userService = userService;
        this.normalService = normalService;
    }

    public ResponseEntity<PressureJournalModel> track(TrackingPressureModel model) {
        WidgetService.checkTime(model.getTime());

        Pressure pressure = new Pressure();
        pressure.setDiastolic(model.getDiastolic());
        pressure.setSystolic(model.getSystolic());
        pressure.setCreatedDate(new Date());
        pressure.setUser(userService.getCurrentUser());
        pressure.setTime(model.getTime());

        return  ResponseEntity.ok(convertToModel(repository.save(pressure)));
    }

    //TO DO
    public PressureJournalModel convertToModel(Pressure pressure){
        Map<String, Double> normalPressureValues = normalService.getNormalPressureValue(userService.getCurrentUser());
        Double systolic = normalPressureValues.get(NormalUserPropertiesService.SYSTOLIC);
        Double diastolic = normalPressureValues.get(NormalUserPropertiesService.DIASTOLIC);
        Boolean isNormal = true;
        return new PressureJournalModel(pressure.getSystolic(), pressure.getDiastolic(), pressure.getTime(), pressure.getCreatedDate(), isNormal);
    }

    public List<PressureJournalModel> convertToModel(List<Pressure> pressures){
        List<PressureJournalModel> list = new ArrayList<>();
        for(Pressure pressure: pressures)
            list.add(convertToModel(pressure));
        return list;
    }

    public ResponseEntity<?> getHistory() {
        return ResponseEntity.ok(convertToModel(repository.findAllByUser(userService.getCurrentUser())));
    }

    public PressureMainPageModel getForMainPage() {
        Optional<Pressure> optionalPressure = repository.findFirstByUserOrderByCreatedDateDesc(userService.getCurrentUser());

        if(optionalPressure.isEmpty())
            return null;
        Pressure pressure = optionalPressure.get();
        PressureMainPageModel model = new PressureMainPageModel();
        model.setDiastolic(pressure.getDiastolic());
        model.setSystolic(pressure.getSystolic());
        model.setTrackedTime(pressure.getCreatedDate());
        return model;

    }
}
