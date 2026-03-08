package programmer.belajar.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmer.belajar.entity.User;
import programmer.belajar.model.ContactResponse;
import programmer.belajar.model.CreateContactRequest;
import programmer.belajar.model.WebResponse;
import programmer.belajar.repository.ContactRepository;
import programmer.belajar.repository.UserRepository;
import programmer.belajar.security.BCrypt;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder .*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders .*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers .*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers .*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setName("Test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000);
        userRepository.save(user);
    }

    @Test
    void createContactBadRequest() throws Exception{
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("");
        request.setEmail("salah");

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createContactSuccsess() throws Exception{
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("abdul");
        request.setLastName("Furnaze");
        request.setEmail("abdul@gmail.com");
        request.setPhone("08555555");

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertNull(response. getErrors());
            assertEquals("abdul", response.getData().getFirstName());
            assertEquals("Furnaze", response.getData().getLastName());
            assertEquals("abdul@gmail.com", response.getData().getEmail());
            assertEquals("08555555", response.getData().getPhone());

            assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }
}