package kg.neobis.diabetes.models.widgets.medication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingMedicationModel {
    private Long medicationId;
    private String time;
    private Double value;

}
