package kg.neobis.diabetes.entity;

import kg.neobis.diabetes.entity.enums.Widgets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_widgets")
public class UserWidgets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    private User user;

    @ElementCollection(targetClass = Widgets.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_widget",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "widget")
    Set<Widgets> widgets;


}
