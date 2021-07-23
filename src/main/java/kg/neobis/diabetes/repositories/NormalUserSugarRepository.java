package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.NormalUserSugar;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NormalUserSugarRepository extends JpaRepository<NormalUserSugar, Long> {

    Optional<NormalUserSugar> findByUser(User user);
}
