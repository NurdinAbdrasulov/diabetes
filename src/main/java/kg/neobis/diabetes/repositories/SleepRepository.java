package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Sleep;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findAllByUser(User user);
//    Optional<Sleep> findFirstByOrderByCreatedDateDesc();
    Optional<Sleep> findFirstByUserOrderByCreatedDateDesc(User user);
}
