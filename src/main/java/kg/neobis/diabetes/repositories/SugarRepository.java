package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.Sugar;
import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserFoodJournal;
import kg.neobis.diabetes.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SugarRepository extends JpaRepository<Sugar, Long> {
    List<Sugar> findAllByUser(User user);
    Optional<Sugar> findFirstByUserOrderByCreatedDateDesc(User user);

}
