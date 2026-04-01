//////////package programmer.belajar.auth;
//////////
//////////import lombok.RequiredArgsConstructor;
//////////import org.springframework.security.authentication.AuthenticationManager;
//////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////////import org.springframework.security.core.userdetails.UserDetails;
//////////import org.springframework.security.crypto.password.PasswordEncoder;
//////////import org.springframework.stereotype.Service;
//////////import programmer.belajar.config.JwtService;
//////////import programmer.belajar.user.Role;
//////////import programmer.belajar.user.User;
//////////import programmer.belajar.user.UserRepository;
////////////import programmer.belajar.entity.User;
//////////
//////////@Service
//////////@RequiredArgsConstructor
//////////
//////////public class AuthenticationService {
//////////
//////////    private final UserRepository repository;
//////////    private final PasswordEncoder passwordEncoder;
//////////
//////////    private final JwtService jwtService;
//////////    private final AuthenticationManager authenticationManager;
//////////
//////////    public AuthenticationResponse register(RegisterRequest request) {
//////////        var user = User.builder()
//////////                .username(request.getEmail())
//////////                .name(request.getFirstname() + " " + request.getLastname())
//////////                .password(passwordEncoder.encode(request.getPassword()))
//////////                .role(Role.USER)
//////////                .build();
//////////
//////////        repository.save(user);
//////////
//////////        var jwtToken = jwtService.generateToken(user);
//////////        return AuthenticationResponse.builder()
//////////                .token(jwtToken)
//////////                .build();
//////////    }
//////////
//////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//////////        authenticationManager.authenticate(
//////////                new UsernamePasswordAuthenticationToken(
//////////                        request.getEmail(),
//////////                        request.getPassword()
//////////        )
//////////                );
//////////
//////////        var user = repository. findByEmail(request.getEmail())
//////////                        .orElseThrow();
//////////        var jwtToken = jwtService.generateToken((UserDetails) user);
//////////        return AuthenticationResponse.builder()
//////////                .token(jwtToken)
//////////                .build();
//////////    }
//////////}
//////////
//////////package programmer.belajar.auth;
//////////
//////////import lombok.RequiredArgsConstructor;
//////////import org.springframework.security.authentication.AuthenticationManager;
//////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////////import org.springframework.security.crypto.password.PasswordEncoder;
//////////import org.springframework.stereotype.Service;
//////////import programmer.belajar.config.JwtService;
//////////import programmer.belajar.user.Role;
//////////import programmer.belajar.user.User;
//////////import programmer.belajar.user.UserRepository;
//////////
//////////@Service
//////////@RequiredArgsConstructor
//////////public class AuthenticationService {
//////////
//////////    private final UserRepository repository;
//////////    private final PasswordEncoder passwordEncoder;
//////////    private final JwtService jwtService;
//////////    private final AuthenticationManager authenticationManager;
//////////
//////////    public AuthenticationResponse register(RegisterRequest request) {
//////////
//////////        if (repository.existsById(request.getEmail())) {
//////////            throw new RuntimeException("Email already registered");
//////////        }
//////////
//////////        var user = User.builder()
//////////                .email(request.getEmail()) // ✅ FIX UTAMA
//////////                .name(request.getFirstname() + " " + request.getLastname())
//////////                .password(passwordEncoder.encode(request.getPassword()))
//////////                .role(Role.USER)
//////////                .build();
//////////
//////////        repository.save(user);
//////////
//////////        var jwtToken = jwtService.generateToken(user);
//////////
//////////        return AuthenticationResponse.builder()
//////////                .token(jwtToken)
//////////                .build();
//////////    }
//////////
//////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//////////
//////////        authenticationManager.authenticate(
//////////                new UsernamePasswordAuthenticationToken(
//////////                        request.getEmail(),
//////////                        request.getPassword()
//////////                )
//////////        );
//////////
//////////        var user = repository.findById(request.getEmail())
//////////                .orElseThrow(() -> new RuntimeException("User not found"));
//////////
//////////        var jwtToken = jwtService.generateToken(user);
//////////
//////////        return AuthenticationResponse.builder()
//////////                .token(jwtToken)
//////////                .build();
//////////    }
//////////}
////////
////////package programmer.belajar.auth;
////////
////////import lombok.RequiredArgsConstructor;
////////import org.springframework.security.authentication.AuthenticationManager;
////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////////import org.springframework.security.crypto.password.PasswordEncoder;
////////import org.springframework.stereotype.Service;
////////import programmer.belajar.config.JwtService;
////////import programmer.belajar.user.Role;
////////import programmer.belajar.user.User;
////////import programmer.belajar.user.UserRepository;
////////
////////@Service
////////@RequiredArgsConstructor
////////public class AuthenticationService {
////////
////////    private final UserRepository repository;
////////    private final PasswordEncoder passwordEncoder;
////////    private final JwtService jwtService;
////////    private final AuthenticationManager authenticationManager;
////////
////////    // ✅ REGISTER
////////    public String register(String email, String password) {
////////
////////        User user = new User();
////////        user.setEmail(email);
////////        user.setPassword(passwordEncoder.encode(password)); // 🔥 WAJIB HASH
////////        user.setRole(Role.USER);
////////
////////        repository.save(user);
////////
////////        return jwtService.generateToken(user);
////////    }
////////
////////    // ✅ LOGIN
////////    public String login(String email, String password) {
////////
////////        authenticationManager.authenticate(
////////                new UsernamePasswordAuthenticationToken(email, password)
////////        );
////////
////////        User user = repository.findByEmail(email)
////////                .orElseThrow();
////////
////////        return jwtService.generateToken(user);
////////    }
////////}
//////
//////
//////package programmer.belajar.auth;
//////
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////import org.springframework.stereotype.Service;
//////import programmer.belajar.config.JwtService;
//////import programmer.belajar.user.Role;
//////import programmer.belajar.user.User;
//////import programmer.belajar.user.UserRepository;
//////
//////@Service
//////@RequiredArgsConstructor
//////public class AuthenticationService {
//////
//////    private final UserRepository repository;
//////    private final PasswordEncoder passwordEncoder;
//////    private final JwtService jwtService;
//////    private final AuthenticationManager authenticationManager;
//////
//////    // ✅ REGISTER
//////    public AuthenticationResponse register(RegisterRequest request) {
//////
//////        User user = new User();
//////        user.setEmail(request.getEmail());
//////        user.setPassword(passwordEncoder.encode(request.getPassword()));
//////        user.setRole(Role.USER);
//////
//////        repository.save(user);
//////
//////        String token = jwtService.generateToken(user);
//////
//////        return AuthenticationResponse.builder()
//////                .token(token)
//////                .build();
//////    }
//////
//////    // ✅ LOGIN
//////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//////
//////        authenticationManager.authenticate(
//////                new UsernamePasswordAuthenticationToken(
//////                        request.getEmail(),
//////                        request.getPassword()
//////                )
//////        );
//////
//////        User user = repository.findByEmail(request.getEmail())
//////                .orElseThrow(() -> new RuntimeException("User not found"));
//////
//////        String token = jwtService.generateToken(user);
//////
//////        return AuthenticationResponse.builder()
//////                .token(token)
//////                .build();
//////    }
//////}
////
////package programmer.belajar.auth;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////import programmer.belajar.config.JwtService;
////import programmer.belajar.user.Role;
////import programmer.belajar.user.User;
////import programmer.belajar.user.UserRepository;
////
////@Service
////@RequiredArgsConstructor
////public class AuthenticationService {
////
////    private final UserRepository repository;
////    private final PasswordEncoder passwordEncoder;
////    private final JwtService jwtService;
////    private final AuthenticationManager authenticationManager;
////
////    public AuthenticationResponse register(RegisterRequest request) {
////
////        User user = new User();
////        user.setEmail(request.getEmail());
////        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        user.setRole(Role.USER);
////
////        repository.save(user);
////
////        String token = jwtService.generateToken(user);
////
////        return AuthenticationResponse.builder()
////                .token(token)
////                .build();
////    }
////
////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        request.getEmail(),
////                        request.getPassword()
////                )
////        );
////
////        User user = repository.findByEmail(request.getEmail())
////                .orElseThrow();
////
////        String token = jwtService.generateToken(user);
////
////        return AuthenticationResponse.builder()
////                .token(token)
////                .build();
////    }
////}
//
//
//package programmer.belajar.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import programmer.belajar.config.JwtService;
//import programmer.belajar.user.Role;
//import programmer.belajar.user.User;
//import programmer.belajar.user.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    // ✅ REGISTER
//    public AuthenticationResponse register(RegisterRequest request) {
//
//        // 🔥 Validation (basic but necessary)
//        if (request.getEmail() == null || request.getEmail().isBlank()) {
//            throw new RuntimeException("Email is required");
//        }
//
//        if (request.getPassword() == null || request.getPassword().length() < 6) {
//            throw new RuntimeException("Password must be at least 6 characters");
//        }
//
//        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
//            throw new RuntimeException("Firstname is required");
//        }
//
//        if (request.getLastname() == null || request.getLastname().isBlank()) {
//            throw new RuntimeException("Lastname is required");
//        }
//
//        // 🔥 Prevent duplicate user
//        if (repository.existsById(request.getEmail())) {
//            throw new RuntimeException("Email already registered");
//        }
//
//        // 🔥 Build user correctly
//        User user = User.builder()
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .name(request.getFirstname() + " " + request.getLastname()) // ✅ FIXED
//                .role(Role.USER)
//                .build();
//
//        repository.save(user);
//
//        String token = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }
//
//    // ✅ LOGIN
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        User user = repository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String token = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }
//}

package programmer.belajar.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import programmer.belajar.config.JwtService;
import programmer.belajar.user.Role;
import programmer.belajar.user.User;
import programmer.belajar.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        // validation
        if (request.getPassword().length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }

        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
            throw new RuntimeException("Firstname is required");
        }

        if (request.getLastname() == null || request.getLastname().isBlank()) {
            throw new RuntimeException("Lastname is required");
        }

        // build user
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getFirstname() + " " + request.getLastname())
                .role(Role.USER)
                .build();

        repository.save(user);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}