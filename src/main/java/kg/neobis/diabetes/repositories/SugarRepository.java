package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Sugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugarRepository extends JpaRepository<Sugar, Long> {
}
