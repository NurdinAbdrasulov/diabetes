package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelToAddNormalUserSleep {
    private Date startTime;
    private Date endTime;
}
