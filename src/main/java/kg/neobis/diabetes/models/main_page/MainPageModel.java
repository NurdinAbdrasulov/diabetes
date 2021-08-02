package kg.neobis.diabetes.models.main_page;

import lombok.Data;

@Data
public class MainPageModel {
    private ActivityMainPageModel activity;
    private FoodMainPageModel food;
    private InsulinMainPageModel insulin;
    private MedicationMainPageModel medication;
    private PressureMainPageModel pressure;
    private SleepMainPageModel sleep;
    private SugarMainPageModel sugar;
    private WaterMainPageModel water;
}
