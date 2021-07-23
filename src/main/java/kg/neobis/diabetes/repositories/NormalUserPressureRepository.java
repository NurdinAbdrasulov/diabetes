package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.NormalUserPressure;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NormalUserPressureRepository extends JpaRepository<NormalUserPressure, Long> {
    Optional<NormalUserPressure> findByUser(User user);
}
