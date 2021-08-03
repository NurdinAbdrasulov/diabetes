package kg.neobis.diabetes.services;

import kg.neobis.diabetes.models.main_page.*;
import kg.neobis.diabetes.services.widget.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MainPageService {
    private final FoodWidgetService foodWidgetService;
    private final InsulinWidgetService insulinWidgetService;
    private final MedicationWidgetService medicationWidgetService;
    private final PhysicalActivityWidgetService activityService;
    private final PressureWidgetService pressureWidgetService;
    private final SleepWidgetService sleepWidgetService;
    private final SugarWidgetService sugarWidgetService;
    private final WaterWidgetService waterWidgetService;

    @Autowired
    public MainPageService(FoodWidgetService foodWidgetService, InsulinWidgetService insulinWidgetService, MedicationWidgetService medicationWidgetService, PhysicalActivityWidgetService activityService, PressureWidgetService pressureWidgetService, SleepWidgetService sleepWidgetService, SugarWidgetService sugarWidgetService, WaterWidgetService waterWidgetService) {
        this.foodWidgetService = foodWidgetService;
        this.insulinWidgetService = insulinWidgetService;
        this.medicationWidgetService = medicationWidgetService;
        this.activityService = activityService;
        this.pressureWidgetService = pressureWidgetService;
        this.sleepWidgetService = sleepWidgetService;
        this.sugarWidgetService = sugarWidgetService;
        this.waterWidgetService = waterWidgetService;
    }


    public ResponseEntity<?> getMainPage() {
        ActivityMainPageModel activityModel = activityService.getForMainPage();
        FoodMainPageModel foodModel = foodWidgetService.getForMainPage();
        InsulinMainPageModel insulinModel = insulinWidgetService.getForMainPage();//
        MedicationMainPageModel medicationModel = medicationWidgetService.getForMainPage();
        PressureMainPageModel pressureModel = pressureWidgetService.getForMainPage();//
        SleepMainPageModel sleepModel = sleepWidgetService.getInfoForMainPage();//
        SugarMainPageModel sugarModel = sugarWidgetService.getForMainPage();//
        WaterMainPageModel waterModel = waterWidgetService.getForMainPage();//


        MainPageModel result = new MainPageModel();
        result.setActivity(activityModel);
        result.setFood(foodModel);
        result.setInsulin(insulinModel);
        result.setMedication(medicationModel);
        result.setPressure(pressureModel);
        result.setSleep(sleepModel);
        result.setSugar(sugarModel);
        result.setWater(waterModel);

        return ResponseEntity.ok(result);
    }
}
