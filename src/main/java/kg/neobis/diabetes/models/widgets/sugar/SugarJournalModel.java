package kg.neobis.diabetes.models.widgets.sugar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SugarJournalModel {

    private Double value;
    private String time;
    private Date createdDate;
    private Boolean isNormal;
}
