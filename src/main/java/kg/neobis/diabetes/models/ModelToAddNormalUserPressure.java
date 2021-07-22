package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelToAddNormalUserPressure {
    private Double systolic;
    private Double diastolic;
}
