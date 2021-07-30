package kg.neobis.diabetes.models.widgets.physical_activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingActivityModel {
    private Long activityId;
    private String time;
    private Double duration;
}
