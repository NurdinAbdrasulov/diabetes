package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Sleep;
import kg.neobis.diabetes.entity.UserPhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPhysicalActivityRepository extends JpaRepository<UserPhysicalActivity, Long> {
    Optional<UserPhysicalActivity> findFirstByOrderByCreatedDateDesc();

}
