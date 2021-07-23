package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.User;
import kg.neobis.diabetes.entity.UserWidgets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WidgetRepository extends JpaRepository<UserWidgets, Long> {
    Optional<UserWidgets> findByUser(User user);
}
