package kg.neobis.diabetes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFoodJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private String time;

    @Column(name = "createdDate")
    private Date createdDate;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "userFoodJournal_Food",
            joinColumns = { @JoinColumn(name = "journal_id") },
            inverseJoinColumns = { @JoinColumn(name = "food_id") }
    )
    private Set<Food> food;
}
