package ma.beldifood.User.repository;

import ma.beldifood.User.dto.UserResponseDto;
import ma.beldifood.User.entities.Status;
import ma.beldifood.User.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userName = ?1 OR u.email = ?1 OR u.phone = ?1")
    Optional<User> findByLogin(String login);

    Optional<User> findByConfirmationToken(String confirmationToken);
    Optional<User> findByResetPasswordToken(String token);
    List<User> findByStatus(Status status);




}
