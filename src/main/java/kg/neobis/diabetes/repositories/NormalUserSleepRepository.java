package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.NormalUserSleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalUserSleepRepository  extends JpaRepository<NormalUserSleep, Long> {
}
