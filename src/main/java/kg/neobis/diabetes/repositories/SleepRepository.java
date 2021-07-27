package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
}
