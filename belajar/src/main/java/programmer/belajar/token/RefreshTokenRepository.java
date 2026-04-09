package programmer.belajar.token;

import org.springframework.data.jpa.repository.JpaRepository;
import programmer.belajar.user.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user); // ✅ FIXED (used in login)
}