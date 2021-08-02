package kg.neobis.diabetes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersWidgetsModel {
    private Set<Long> widgetIds;
//    private ModelToAddNormalUserPressure userPressure;
//    private ModelToAddNormalUserSleep userSleep;
//    private ModelToAddNormalUserSugar userSugar;
}
