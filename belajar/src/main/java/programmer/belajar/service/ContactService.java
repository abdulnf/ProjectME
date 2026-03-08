package programmer.belajar.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmer.belajar.entity.Contact;
import programmer.belajar.entity.User;
import programmer.belajar.model.ContactResponse;
import programmer.belajar.model.CreateContactRequest;
import programmer.belajar.repository.ContactRepository;

import java.util.UUID;

@Service

public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public ContactResponse create(User user, CreateContactRequest request){
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstname(request.getFirstName());
        contact.setLastname(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstname())
                .lastName(contact.getLastname())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}