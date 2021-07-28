package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.UserPhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhysicalActivityRepository extends JpaRepository<UserPhysicalActivity, Long> {
}
