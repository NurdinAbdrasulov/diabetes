package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Insulin;
import kg.neobis.diabetes.entity.Pressure;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsulinRepository extends JpaRepository<Insulin, Long> {
    List<Insulin> findAllByUser(User user);

}
