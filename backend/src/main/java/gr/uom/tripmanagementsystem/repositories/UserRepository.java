package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndAndPassword(String username, String password);
}
