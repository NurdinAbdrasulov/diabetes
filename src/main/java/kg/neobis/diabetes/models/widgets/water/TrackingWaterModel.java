package kg.neobis.diabetes.models.widgets.water;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingWaterModel {
    private String time;
    private Double value;
}
