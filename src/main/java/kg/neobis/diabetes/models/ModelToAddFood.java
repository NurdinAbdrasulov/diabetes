package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelToAddFood {
    private String name;
    private Long categoryId;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Double calories;
}
