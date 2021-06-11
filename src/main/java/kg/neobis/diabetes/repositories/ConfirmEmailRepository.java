package kg.neobis.diabetes.repositories;

import kg.neobis.diabetes.entity.ConfirmEmail;
import kg.neobis.diabetes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepository extends JpaRepository<ConfirmEmail, Long> {
    ConfirmEmail findByEmail(String email);
    ConfirmEmail findByCode(String code);
}