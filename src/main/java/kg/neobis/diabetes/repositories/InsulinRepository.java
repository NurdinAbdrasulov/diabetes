package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Insulin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsulinRepository extends JpaRepository<Insulin, Long> {
}
