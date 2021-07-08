package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.PhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalActivityRepository extends JpaRepository<PhysicalActivity, Long> {
}
