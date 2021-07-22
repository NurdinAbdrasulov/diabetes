package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationModel {
    private Long id;
    private String name;
    private Long userId;
}
