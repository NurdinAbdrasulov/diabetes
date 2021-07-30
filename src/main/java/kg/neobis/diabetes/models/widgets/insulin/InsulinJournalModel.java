package kg.neobis.diabetes.models.widgets.insulin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsulinJournalModel {
    private String time;
    private Date createdDate;
    private Double value;
}
