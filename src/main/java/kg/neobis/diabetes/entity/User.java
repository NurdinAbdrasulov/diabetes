package kg.neobis.diabetes.entity;

import kg.neobis.diabetes.entity.enums.DiabetesStatus;
import kg.neobis.diabetes.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "date_of_birth")
    private Date birthDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender_id")
    private Gender gender;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "diabetes_status_id")
    private DiabetesStatus diabetesStatus;
}
