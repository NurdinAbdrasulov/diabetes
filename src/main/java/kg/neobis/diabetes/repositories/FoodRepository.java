package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Food;
import kg.neobis.diabetes.entity.FoodCategory;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByCategory(FoodCategory category);
    List<Food> findAllByUserIsNull();
    List<Food> findAllByUser(User user);
    List<Food> findAllByUserIsNullAndCategory(FoodCategory category);
}
