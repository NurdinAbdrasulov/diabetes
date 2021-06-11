package kg.neobis.diabetes.models;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationModel {
    private String email;
    private String password;
    private String confirmPassword;
}
