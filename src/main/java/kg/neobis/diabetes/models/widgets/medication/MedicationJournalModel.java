package kg.neobis.diabetes.models.widgets.medication;

import kg.neobis.diabetes.models.MedicationModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationJournalModel {
    private MedicationModel medication;
    private String time;
    private Double value;
    private Date createdDate;
}
