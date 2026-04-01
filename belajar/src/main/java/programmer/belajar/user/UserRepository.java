package programmer.belajar.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
//import programmer.belajar.entity.User;

@Repository



public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

//    Optional<User> findFirstByToken(String token);


}

//package programmer.belajar.user;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, String> {
//
//    Optional<User> findFirstByToken(String token);
//}