package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserFoodJournal;
import kg.neobis.diabetes.entity.UserMedication;
import kg.neobis.diabetes.entity.UserPhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFoodJournal, Long> {
    List<UserFoodJournal> findAllByUser(User user);
    List<UserFoodJournal> findAllByUserAndCreatedDateAfter(User user, Date date);

}
