package kg.neobis.diabetes.models.widgets.physical_activity;

import kg.neobis.diabetes.models.PhysicalActivityModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityJournalModel {
    private PhysicalActivityModel activity;
    private String time;
    private Double duration;
    private Date createdDate;

}
