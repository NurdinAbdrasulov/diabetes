package kg.neobis.diabetes.models.widgets.insulin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingInsulinModel {
    private String time;
    private Double value;
}
