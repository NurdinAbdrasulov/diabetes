package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.RestorePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestorePasswordRepository extends JpaRepository<RestorePassword, Long> {
    RestorePassword findByEmail(String email);
}
