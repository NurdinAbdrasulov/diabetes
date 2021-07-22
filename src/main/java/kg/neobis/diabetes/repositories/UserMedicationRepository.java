package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.UserMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMedicationRepository extends JpaRepository<UserMedication, Long> {
}
