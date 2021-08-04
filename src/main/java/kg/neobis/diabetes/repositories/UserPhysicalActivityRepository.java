package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserPhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserPhysicalActivityRepository extends JpaRepository<UserPhysicalActivity, Long> {
    List<UserPhysicalActivity> findAllByCreatedDateAfter(Date date);
    List<UserPhysicalActivity> findAllByUserAndCreatedDateAfter(User user, Date date);

}
