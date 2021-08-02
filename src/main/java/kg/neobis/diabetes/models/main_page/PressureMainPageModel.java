package kg.neobis.diabetes.models.main_page;

import lombok.Data;

import java.util.Date;

@Data
public class PressureMainPageModel {
    private Double systolic;
    private Double diastolic;
    private Date trackedTime;
}
