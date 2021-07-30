package kg.neobis.diabetes.models.widgets.pressure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PressureJournalModel {

    private Double systolic;
    private Double diastolic;
    private String time;
    private Date createdDate;
    private Boolean isNormal;

}
