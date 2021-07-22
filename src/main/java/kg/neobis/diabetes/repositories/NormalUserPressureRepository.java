package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.NormalUserPressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalUserPressureRepository extends JpaRepository<NormalUserPressure, Long> {
}
