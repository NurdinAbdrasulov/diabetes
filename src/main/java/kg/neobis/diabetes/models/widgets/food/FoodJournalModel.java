package kg.neobis.diabetes.models.widgets.food;

import kg.neobis.diabetes.models.FoodModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodJournalModel {
    private String time;
    private Date createdDate;
    private List<FoodModel> food;
}
