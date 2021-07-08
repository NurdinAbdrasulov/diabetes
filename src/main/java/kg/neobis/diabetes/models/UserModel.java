package kg.neobis.diabetes.models;

import kg.neobis.diabetes.entity.enums.DiabetesStatus;
import kg.neobis.diabetes.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private Long id;
    private String email;
    private String name;
    private Date birthDate;
    private Gender gender;
    private Double weight;
    private Double height;
    private DiabetesStatus diabetesStatus;
}
