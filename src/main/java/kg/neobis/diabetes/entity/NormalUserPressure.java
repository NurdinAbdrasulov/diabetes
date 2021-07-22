package kg.neobis.diabetes.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NormalUserPressure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "systolic", nullable = false)
    private Double systolic;

    @Column(name = "diastolic", nullable = false)
    private Double diastolic;

    @ManyToOne
    private User user;
}
