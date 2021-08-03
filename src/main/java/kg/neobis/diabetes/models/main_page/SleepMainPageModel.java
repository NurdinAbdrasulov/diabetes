package kg.neobis.diabetes.models.main_page;

import lombok.Data;

import java.util.Date;

@Data
public class SleepMainPageModel {
    private String duration;
    private String startTime;
    private String endTime;
    private Date trackedDate;
}
