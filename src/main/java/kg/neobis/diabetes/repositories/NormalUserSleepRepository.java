package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.NormalUserSleep;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NormalUserSleepRepository  extends JpaRepository<NormalUserSleep, Long> {

    Optional<NormalUserSleep> findByUser(User user);
}
