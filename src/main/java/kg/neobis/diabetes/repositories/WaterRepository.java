package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Sleep;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterRepository extends JpaRepository<Water, Long> {
    List<Water> findAllByUser(User user);

}
