package kg.neobis.diabetes.models.widgets.sugar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingSugarModel {

    private Double value;
    private String time;
}
