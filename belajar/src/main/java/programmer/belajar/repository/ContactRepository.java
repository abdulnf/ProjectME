package programmer.belajar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import programmer.belajar.entity.Contact;
import programmer.belajar.user.User;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> , JpaSpecificationExecutor<Contact> {
    Optional<Contact> findFirstByUserAndId(User user, String id);
}
