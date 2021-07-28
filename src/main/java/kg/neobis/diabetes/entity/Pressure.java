package kg.neobis.diabetes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pressure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "systolic")
    private Double systolic;

    @Column(name = "diastolic")
    private Double diastolic;

    @Column(length = 10)
    private String time;

    @Column(name = "created_date")
    private Date createdDate;
}
