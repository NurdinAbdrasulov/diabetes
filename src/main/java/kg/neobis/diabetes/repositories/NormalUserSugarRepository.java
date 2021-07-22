package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.NormalUserSugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalUserSugarRepository extends JpaRepository<NormalUserSugar, Long> {
}
