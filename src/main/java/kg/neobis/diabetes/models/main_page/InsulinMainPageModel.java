package kg.neobis.diabetes.models.main_page;

import lombok.Data;

import java.util.Date;

@Data
public class InsulinMainPageModel {
    private Double value;
    private Date trackedTime;
}
