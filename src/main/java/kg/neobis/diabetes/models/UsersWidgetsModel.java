package kg.neobis.diabetes.models;

import lombok.Data;

import java.util.Set;

@Data
public class UsersWidgetsModel {
    private Set<Long> widgetIds;
}
