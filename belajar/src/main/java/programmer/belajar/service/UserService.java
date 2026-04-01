////package programmer.belajar.service;
////
////import jakarta.transaction.Transactional;
////import jakarta.validation.Validator;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.stereotype.Service;
////import org.springframework.web.server.ResponseStatusException;
//////import programmer.belajar.entity.User;
////import programmer.belajar.user.User;
////import programmer.belajar.model.RegisterUserRequest;
////import programmer.belajar.model.UpdateUserRequest;
////import programmer.belajar.model.UserResponse;
////import programmer.belajar.security.BCrypt;
////import programmer.belajar.user.UserRepository;
////
////import java.util.Objects;
////
////@Service
////public class UserService {
////    @Autowired
////    private UserRepository userRepository;
////    @Autowired
////    private ValidationService validationService;
////
////    @Autowired
////    private Validator validator;
////
////    @Transactional
////
////    public void register (RegisterUserRequest request){
////        validationService.validate(request);
////
////        if (userRepository.existsById(request.getUsername())){
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already registered");
////
////        }
////        User user = new User();
////        user.setUsername(request.getUsername());
////        user.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));
////        user.setName(request.getName());
////
////        userRepository.save(user);
////    }
////    public UserResponse get(User user) {
////        return UserResponse.builder()
////                .username(user.getUsername())
////                .name(user.getName())
////                .build();
////    }
////    @Transactional
////    public UserResponse update(User user, UpdateUserRequest request){
////        validationService.validate(request);
////        if (Objects.nonNull(request.getName())){
////            user.setName(request.getName());
////
////        }
////
////        if (Objects.nonNull(request.getPassword())) {
////            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
////        }
////
////        userRepository.save(user);
////
////        return UserResponse.builder()
////                .name(user.getName())
////                .username(user.getUsername())
////                .build();
////        }
////
////
////}
////
////
//
//
//package programmer.belajar.service;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//import programmer.belajar.user.User;
//import programmer.belajar.user.UserRepository;
//import programmer.belajar.security.BCrypt;
//import programmer.belajar.auth.RegisterRequest;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Transactional
//    public void register(RegisterRequest request){
//
//        if (userRepository.existsById(request.getEmail())){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already registered");
//        }
//
//        User user = new User();
//        user.setUsername(request.getEmail()); // email jadi username
//        user.setEmail(request.getEmail());
//        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
//        user.setName(request.getFirstname() + " " + request.getLastname());
//
//        userRepository.save(user);
//    }
//}

package programmer.belajar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer.belajar.auth.RegisterRequest;
import programmer.belajar.model.UpdateUserRequest;
import programmer.belajar.model.UserResponse;
import programmer.belajar.user.User;
import programmer.belajar.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 🔥 REGISTER
    public void register(RegisterRequest request) {
        if (userRepository.existsById(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword()) // ⚠️ HARUS di-encode di AuthenticationService, bukan di sini
                .name(request.getFirstname() + " " + request.getLastname())
                .build();

        userRepository.save(user);
    }

    // 🔥 GET CURRENT USER
    public UserResponse get(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    // 🔥 UPDATE USER
    public UserResponse update(User user, UpdateUserRequest request) {

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getPassword() != null) {
            user.setPassword(request.getPassword()); // ⚠️ harus encode juga kalau dipakai
        }

        userRepository.save(user);

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}