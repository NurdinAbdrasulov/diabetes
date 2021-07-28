package kg.neobis.diabetes.models.widgets.water;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterModel {

    private String time;
    private Double value;
    private Date createdDate;
}
