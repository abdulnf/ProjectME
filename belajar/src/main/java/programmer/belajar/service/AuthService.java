package programmer.belajar.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer.belajar.entity.User;
import programmer.belajar.model.LoginUserRequest;
import programmer.belajar.model.TokenResponse;
import programmer.belajar.repository.UserRepository;
import programmer.belajar.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ValidationService validationService;
    @Transactional
    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong "));

        if (BCrypt.checkpw(request.getPassword(),user.getPassword())){
            //sukses
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiredAt(next30Days());
        userRepository.save(user);

        return TokenResponse.builder()
                .token(user.getToken())
                .expireAt(user.getTokenExpiredAt())
                .build();

        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong ");
        }

    }

    private Long next30Days(){
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);

    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }
}
