//
////////package programmer.belajar.service;
////////
////////import lombok.RequiredArgsConstructor;
////////import org.springframework.http.HttpStatus;
////////import org.springframework.security.crypto.password.PasswordEncoder;
////////import org.springframework.stereotype.Service;
////////import org.springframework.web.server.ResponseStatusException;
////////import programmer.belajar.auth.RegisterRequest;
////////import programmer.belajar.model.UpdateUserRequest;
////////import programmer.belajar.model.UserResponse;
////////import programmer.belajar.user.Role;
////////import programmer.belajar.user.User;
////////import programmer.belajar.user.UserRepository;
////////
////////@Service
////////@RequiredArgsConstructor
////////public class UserService {
////////
////////    private final UserRepository userRepository;
////////    private final PasswordEncoder passwordEncoder;
////////
////////    public void register(RegisterRequest request) {
////////        if (userRepository.existsById(request.getEmail())) {
////////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
////////        }
////////
////////        User user = User.builder()
////////                .email(request.getEmail())
////////                .password(passwordEncoder.encode(request.getPassword()))
////////                .name(request.getFirstname() + " " + request.getLastname())
////////                .role(Role.USER) // default
////////                .build();
////////
////////        userRepository.save(user);
////////    }
////////
////////    public UserResponse get(User user) {
////////        return UserResponse.builder()
////////                .email(user.getEmail())
////////                .name(user.getName())
////////                .build();
////////    }
////////
////////    public UserResponse update(User user, UpdateUserRequest request) {
////////
////////        if (request.getName() != null) {
////////            user.setName(request.getName());
////////        }
////////
////////        if (request.getPassword() != null) {
////////            user.setPassword(passwordEncoder.encode(request.getPassword()));
////////        }
////////
////////        userRepository.save(user);
////////
////////        return UserResponse.builder()
////////                .email(user.getEmail())
////////                .name(user.getName())
////////                .build();
////////    }
////////
////////    public void delete(User user) {
////////        if (!userRepository.existsById(user.getEmail())) {
////////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
////////        }
////////        userRepository.deleteById(user.getEmail());
////////    }
////////    public void deleteByAdmin(String email) {
////////        if (!userRepository.existsById(email)) {
////////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
////////        }
////////        userRepository.deleteById(email);
////////    }
////////}
//////
//////
//////
//////package programmer.belajar.service;
//////
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.http.HttpStatus;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////import org.springframework.stereotype.Service;
//////import org.springframework.web.server.ResponseStatusException;
//////import programmer.belajar.model.UpdateUserRequest;
//////import programmer.belajar.model.UserResponse;
//////import programmer.belajar.user.User;
//////import programmer.belajar.user.UserRepository;
//////
//////@Service
//////@RequiredArgsConstructor
//////public class UserService {
//////
//////    private final UserRepository userRepository;
//////    private final PasswordEncoder passwordEncoder;
//////
//////    // =========================
//////    // ✅ GET CURRENT USER
//////    // =========================
//////    public UserResponse get(User user) {
//////        return UserResponse.builder()
//////                .email(user.getEmail())
//////                .name(user.getName())
//////                .build();
//////    }
//////
//////    // =========================
//////    // ✅ UPDATE CURRENT USER
//////    // =========================
//////    public UserResponse update(User user, UpdateUserRequest request) {
//////
//////        if (request.getName() != null) {
//////            user.setName(request.getName());
//////        }
//////
//////        if (request.getPassword() != null) {
//////            user.setPassword(passwordEncoder.encode(request.getPassword()));
//////        }
//////
//////        userRepository.save(user);
//////
//////        return UserResponse.builder()
//////                .email(user.getEmail())
//////                .name(user.getName())
//////                .build();
//////    }
//////
//////    // =========================
//////    // 🔥 ADMIN: DELETE USER
//////    // =========================
//////    public void deleteByAdmin(String email) {
//////
//////        if (!userRepository.existsById(email)) {
//////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//////        }
//////
//////        userRepository.deleteById(email);
//////    }
//////
//////    // =========================
//////    // 🔥 ADMIN: UPDATE USER
//////    // =========================
//////    public UserResponse updateByAdmin(String email, UpdateUserRequest request) {
//////
//////        User user = userRepository.findById(email)
//////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//////
//////        if (request.getName() != null) {
//////            user.setName(request.getName());
//////        }
//////
//////        if (request.getPassword() != null) {
//////            user.setPassword(passwordEncoder.encode(request.getPassword()));
//////        }
//////
//////        userRepository.save(user);
//////
//////        return UserResponse.builder()
//////                .email(user.getEmail())
//////                .name(user.getName())
//////                .build();
//////    }
//////}
////
////package programmer.belajar.service;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.HttpStatus;
////import org.springframework.stereotype.Service;
////import org.springframework.web.server.ResponseStatusException;
////import programmer.belajar.model.UpdateUserRequest;
////import programmer.belajar.model.UserResponse;
////import programmer.belajar.user.User;
////import programmer.belajar.user.UserRepository;
////
////@Service
////@RequiredArgsConstructor
////public class UserService {
////
////    private final UserRepository userRepository;
////
////    // =========================
////    // 🔥 ADMIN DELETE
////    // =========================
////    public void deleteByAdmin(String targetEmail, String currentAdminEmail) {
////
////        // ❌ prevent self delete
////        if (targetEmail.equals(currentAdminEmail)) {
////            throw new ResponseStatusException(
////                    HttpStatus.BAD_REQUEST,
////                    "Admin cannot delete itself"
////            );
////        }
////
////        if (!userRepository.existsById(targetEmail)) {
////            throw new ResponseStatusException(
////                    HttpStatus.NOT_FOUND,
////                    "User not found"
////            );
////        }
////
////        userRepository.deleteById(targetEmail);
////    }
////
////    // =========================
////    // 🔥 ADMIN UPDATE
////    // =========================
////    public UserResponse updateByAdmin(
////            String targetEmail,
////            String currentAdminEmail,
////            UpdateUserRequest request
////    ) {
////
////        User user = userRepository.findById(targetEmail)
////                .orElseThrow(() -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND,
////                        "User not found"
////                ));
////
////        // ⚠️ optional protection (recommended)
////        if (targetEmail.equals(currentAdminEmail)) {
////            throw new ResponseStatusException(
////                    HttpStatus.BAD_REQUEST,
////                    "Admin cannot update itself here"
////            );
////        }
////
////        if (request.getName() != null) {
////            user.setName(request.getName());
////        }
////
////        if (request.getPassword() != null) {
////            user.setPassword(request.getPassword()); // encode if needed
////        }
////
////        userRepository.save(user);
////
////        return UserResponse.builder()
////                .email(user.getEmail())
////                .name(user.getName())
////                .build();
////    }
////}
//
//package programmer.belajar.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//import programmer.belajar.model.UpdateRoleRequest;
//import programmer.belajar.model.UpdateUserRequest;
//import programmer.belajar.model.UserResponse;
//import programmer.belajar.user.User;
//import programmer.belajar.user.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    // =========================
//    // 🔥 GET CURRENT USER
//    // =========================
//    public UserResponse get(User user) {
//        return UserResponse.builder()
//                .email(user.getEmail())
//                .name(user.getName())
//                .build();
//    }
//
//    // =========================
//    // 🔥 UPDATE CURRENT USER
//    // =========================
//    public UserResponse update(User user, UpdateUserRequest request) {
//
//        if (request.getName() != null) {
//            user.setName(request.getName());
//        }
//
//        if (request.getPassword() != null) {
//            user.setPassword(request.getPassword()); // encode if needed
//        }
//
//        userRepository.save(user);
//
//        return UserResponse.builder()
//                .email(user.getEmail())
//                .name(user.getName())
//                .build();
//    }
//
//    public void deleteByAdmin(String targetEmail, String currentAdminEmail) {
//
//        System.out.println("TARGET: " + targetEmail);
//        System.out.println("CURRENT: " + currentAdminEmail);
//
//        if (targetEmail.equalsIgnoreCase(currentAdminEmail)) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Admin cannot delete itself"
//            );
//        }
//
//        if (!userRepository.existsById(targetEmail)) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,
//                    "User not found"
//            );
//        }
//
//        userRepository.deleteById(targetEmail);
//    }
//
//
//    public UserResponse updateByAdmin(
//            String targetEmail,
//            String currentAdminEmail,
//            UpdateUserRequest request
//    ) {
//
//        System.out.println("UPDATE TARGET: " + targetEmail);
//        System.out.println("UPDATE CURRENT: " + currentAdminEmail);
//
//        if (targetEmail.equalsIgnoreCase(currentAdminEmail)) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Admin cannot update itself here"
//            );
//        }
//
//        User user = userRepository.findById(targetEmail)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        if (request.getName() != null) {
//            user.setName(request.getName());
//        }
//
//        if (request.getPassword() != null) {
//            user.setPassword(request.getPassword()); // ⚠️ encode this later
//        }
//
//        userRepository.save(user);
//
//        return UserResponse.builder()
//                .email(user.getEmail())
//                .name(user.getName())
//                .build();
//    }
//
//    public void updateRoleByAdmin(
//            String targetEmail,
//            String currentAdminEmail,
//            UpdateRoleRequest request
//    ) {
//
//        System.out.println("ROLE TARGET: " + targetEmail);
//        System.out.println("ROLE CURRENT: " + currentAdminEmail);
//
//        // ❌ prevent admin changing itself
//        if (targetEmail.equalsIgnoreCase(currentAdminEmail)) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Admin cannot change its own role"
//            );
//        }
//
//        User user = userRepository.findById(targetEmail)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND,
//                        "User not found"
//                ));
//
//        if (request.getRole() == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Role must not be null"
//            );
//        }
//
//        user.setRole(request.getRole());
//
//        userRepository.save(user);
//    }
//}
//


package programmer.belajar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer.belajar.model.UpdateRoleRequest;
import programmer.belajar.model.UpdateUserRequest;
import programmer.belajar.model.UserResponse;
import programmer.belajar.user.User;
import programmer.belajar.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // 🔥 GET CURRENT USER
    // =========================
    public UserResponse get(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    // =========================
    // 🔥 UPDATE CURRENT USER
    // =========================
    public UserResponse update(User user, UpdateUserRequest request) {

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    // =========================
    // 🔥 ADMIN DELETE
    // =========================
    public void deleteByAdmin(String targetEmail, String currentAdminEmail) {

        if (targetEmail.equalsIgnoreCase(currentAdminEmail)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Admin cannot delete itself"
            );
        }

        if (!userRepository.existsById(targetEmail)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );
        }

        userRepository.deleteById(targetEmail);
    }

    // =========================
    // 🔥 ADMIN UPDATE USER
    // =========================
    public UserResponse updateByAdmin(
            String targetEmail,
            String currentAdminEmail,
            UpdateUserRequest request
    ) {

        if (targetEmail.equalsIgnoreCase(currentAdminEmail)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Admin cannot update itself here"
            );
        }

        User user = userRepository.findById(targetEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    // =========================
    // 🔥 ADMIN UPDATE ROLE
    // =========================
    public void updateRoleByAdmin(
            String targetEmail,
            String currentAdminEmail,
            UpdateRoleRequest request
    ) {

        if (targetEmail.equalsIgnoreCase(currentAdminEmail)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Admin cannot change its own role"
            );
        }

        User user = userRepository.findById(targetEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        if (request.getRole() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Role must not be null"
            );
        }

        user.setRole(request.getRole());
        userRepository.save(user);
    }
    // ✅ GET ALL USERS
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .role(user.getRole())
                        .build()
                )
                .toList();
    }
}