package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WidgetsAndNormalValuesModel {
    private List<WidgetModel> widgets;
    private ModelToAddNormalUserPressure userPressure;
    private ModelToAddNormalUserSleep userSleep;
    private ModelToAddNormalUserSugar userSugar;
    private List<MedicationModel> medications;
}
