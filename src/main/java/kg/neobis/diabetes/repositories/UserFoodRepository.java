package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
