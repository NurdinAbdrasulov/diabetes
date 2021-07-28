package kg.neobis.diabetes.models.widgets.sleep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingSleepModel {
    private String startTime;
    private String endTime;
}
