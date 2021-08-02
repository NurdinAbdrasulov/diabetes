package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Pressure;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PressureRepository extends JpaRepository<Pressure, Long> {
    List<Pressure> findAllByUser(User user);

}
