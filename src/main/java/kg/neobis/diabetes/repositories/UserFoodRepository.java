package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.UserFoodJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFoodJournal, Long> {
}
