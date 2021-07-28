package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelToAddFoodByUser {
    private String name;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Double calories;
}
