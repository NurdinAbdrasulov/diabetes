package kg.neobis.diabetes.models.widgets.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingFoodModel {
    private Date time;
    private Long[] foodIds;
}
