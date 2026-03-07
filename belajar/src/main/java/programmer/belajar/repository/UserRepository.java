package programmer.belajar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmer.belajar.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, String> {
}
