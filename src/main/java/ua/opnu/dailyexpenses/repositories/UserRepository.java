package ua.opnu.dailyexpenses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.dailyexpenses.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);

}
