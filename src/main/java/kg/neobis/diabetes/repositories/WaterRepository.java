package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRepository extends JpaRepository<Water, Long> {
}
