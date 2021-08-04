package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserMedication;
import kg.neobis.diabetes.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMedicationRepository extends JpaRepository<UserMedication, Long> {
    List<UserMedication> findAllByUser(User user);
    Optional<UserMedication> findFirstByUserOrderByCreatedDateDesc(User user);

}
