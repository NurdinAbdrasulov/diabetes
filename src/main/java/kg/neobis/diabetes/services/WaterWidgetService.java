package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Water;
import kg.neobis.diabetes.exception.WrongDataException;
import kg.neobis.diabetes.models.widgets.water.TrackingWaterModel;
import kg.neobis.diabetes.models.widgets.water.WaterModel;
import kg.neobis.diabetes.repositories.WaterRepository;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WaterWidgetService {
    private final WaterRepository repository;
    private final MyUserServiceImpl userService;

    @Autowired
    public WaterWidgetService(WaterRepository repository, MyUserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<WaterModel> track(TrackingWaterModel model){

        WidgetService.checkTime(model.getTime());

        Water water = new Water();
        water.setTime(model.getTime());
        water.setCreatedDate(new Date());
        water.setUser(userService.getCurrentUser());
        water.setValue(model.getValue());

        return ResponseEntity.ok(convertToModel(repository.save(water)));

    }

    private WaterModel convertToModel(Water water){
        return new WaterModel(water.getTime(), water.getValue(), water.getCreatedDate());
    }

    private List<WaterModel> convertToModel(List<Water> waterList){
        List<WaterModel> list = new ArrayList<>();

        for(Water water : waterList)
            list.add(convertToModel(water));

        return list;
    }

    public ResponseEntity<?> getHistory() {
        return ResponseEntity.ok(convertToModel(repository.findAll()));
    }
}
