package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserFoodJournal;
import kg.neobis.diabetes.entity.UserMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFoodJournal, Long> {
    List<UserFoodJournal> findAllByUser(User user);

}
