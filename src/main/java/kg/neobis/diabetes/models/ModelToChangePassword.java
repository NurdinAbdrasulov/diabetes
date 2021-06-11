package kg.neobis.diabetes.models;

import lombok.Data;

@Data
public class ModelToChangePassword {
    private String oldPassword;
    private String newPassword;
}
