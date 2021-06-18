package kg.neobis.diabetes.models;

import lombok.Data;

@Data
public class RestorePasswordModel {
    private String password;
    private String confirmPassword;
}