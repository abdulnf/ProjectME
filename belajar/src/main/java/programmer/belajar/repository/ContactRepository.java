package programmer.belajar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmer.belajar.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
