package kg.neobis.diabetes.models.main_page;

import lombok.Data;

@Data
public class SleepMainPageModel {
    private Integer hour;
    private Integer minutes;
    private String startTime;
    private String endTime;
}
