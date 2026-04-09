
////////////////package programmer.belajar.auth;
////////////////
////////////////import lombok.RequiredArgsConstructor;
////////////////import org.springframework.security.authentication.AuthenticationManager;
////////////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////////////////import org.springframework.security.crypto.password.PasswordEncoder;
////////////////import org.springframework.stereotype.Service;
////////////////import programmer.belajar.config.JwtService;
////////////////import programmer.belajar.user.Role;
////////////////import programmer.belajar.user.User;
////////////////import programmer.belajar.user.UserRepository;
////////////////
////////////////@Service
////////////////@RequiredArgsConstructor
////////////////public class AuthenticationService {
////////////////
////////////////    private final UserRepository repository;
////////////////    private final PasswordEncoder passwordEncoder;
////////////////    private final JwtService jwtService;
////////////////    private final AuthenticationManager authenticationManager;
////////////////
////////////////    // ✅ REGISTER
////////////////    public AuthenticationResponse register(RegisterRequest request) {
////////////////
////////////////        // 🔥 Validation (basic but necessary)
////////////////        if (request.getEmail() == null || request.getEmail().isBlank()) {
////////////////            throw new RuntimeException("Email is required");
////////////////        }
////////////////
////////////////        if (request.getPassword() == null || request.getPassword().length() < 6) {
////////////////            throw new RuntimeException("Password must be at least 6 characters");
////////////////        }
////////////////
////////////////        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
////////////////            throw new RuntimeException("Firstname is required");
////////////////        }
////////////////
////////////////        if (request.getLastname() == null || request.getLastname().isBlank()) {
////////////////            throw new RuntimeException("Lastname is required");
////////////////        }
////////////////
////////////////        // 🔥 Prevent duplicate user
////////////////        if (repository.existsById(request.getEmail())) {
////////////////            throw new RuntimeException("Email already registered");
////////////////        }
////////////////
////////////////        // 🔥 Build user correctly
////////////////        User user = User.builder()
////////////////                .email(request.getEmail())
////////////////                .password(passwordEncoder.encode(request.getPassword()))
////////////////                .name(request.getFirstname() + " " + request.getLastname()) // ✅ FIXED
////////////////                .role(Role.USER)
////////////////                .build();
////////////////
////////////////        repository.save(user);
////////////////
////////////////        String token = jwtService.generateToken(user);
////////////////
////////////////        return AuthenticationResponse.builder()
////////////////                .token(token)
////////////////                .build();
////////////////    }
////////////////
////////////////    // ✅ LOGIN
////////////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////////////////
////////////////        authenticationManager.authenticate(
////////////////                new UsernamePasswordAuthenticationToken(
////////////////                        request.getEmail(),
////////////////                        request.getPassword()
////////////////                )
////////////////        );
////////////////
////////////////        User user = repository.findByEmail(request.getEmail())
////////////////                .orElseThrow(() -> new RuntimeException("User not found"));
////////////////
////////////////        String token = jwtService.generateToken(user);
////////////////
////////////////        return AuthenticationResponse.builder()
////////////////                .token(token)
////////////////                .build();
////////////////    }
////////////////}
//////////////
//////////////package programmer.belajar.auth;
//////////////
//////////////import lombok.RequiredArgsConstructor;
//////////////import org.springframework.security.authentication.AuthenticationManager;
//////////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////////////import org.springframework.security.crypto.password.PasswordEncoder;
//////////////import org.springframework.stereotype.Service;
//////////////import programmer.belajar.config.JwtService;
//////////////import programmer.belajar.user.Role;
//////////////import programmer.belajar.user.User;
//////////////import programmer.belajar.user.UserRepository;
//////////////
//////////////@Service
//////////////@RequiredArgsConstructor
//////////////public class AuthenticationService {
//////////////
//////////////    private final UserRepository repository;
//////////////    private final PasswordEncoder passwordEncoder;
//////////////    private final JwtService jwtService;
//////////////    private final AuthenticationManager authenticationManager;
//////////////
//////////////    public AuthenticationResponse register(RegisterRequest request) {
//////////////
//////////////        // validation
//////////////        if (request.getPassword().length() < 6) {
//////////////            throw new RuntimeException("Password must be at least 6 characters");
//////////////        }
//////////////
//////////////        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
//////////////            throw new RuntimeException("Firstname is required");
//////////////        }
//////////////
//////////////        if (request.getLastname() == null || request.getLastname().isBlank()) {
//////////////            throw new RuntimeException("Lastname is required");
//////////////        }
//////////////
//////////////        // build user
//////////////        User user = User.builder()
//////////////                .email(request.getEmail())
//////////////                .password(passwordEncoder.encode(request.getPassword()))
//////////////                .name(request.getFirstname() + " " + request.getLastname())
//////////////                .role(Role.USER)
//////////////                .build();
//////////////
//////////////        repository.save(user);
//////////////
//////////////        String token = jwtService.generateToken(user);
//////////////
//////////////        return AuthenticationResponse.builder()
//////////////                .token(token)
//////////////                .build();
//////////////    }
//////////////
//////////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//////////////
//////////////        authenticationManager.authenticate(
//////////////                new UsernamePasswordAuthenticationToken(
//////////////                        request.getEmail(),
//////////////                        request.getPassword()
//////////////                )
//////////////        );
//////////////
//////////////        User user = repository.findByEmail(request.getEmail())
//////////////                .orElseThrow(() -> new RuntimeException("User not found"));
//////////////
//////////////        String token = jwtService.generateToken(user);
//////////////
//////////////        return AuthenticationResponse.builder()
//////////////                .token(token)
//////////////                .build();
//////////////    }
//////////////}
////////////
////////////package programmer.belajar.auth;
////////////
////////////import lombok.RequiredArgsConstructor;
////////////import org.springframework.security.authentication.AuthenticationManager;
////////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////////////import org.springframework.security.crypto.password.PasswordEncoder;
////////////import org.springframework.stereotype.Service;
////////////import programmer.belajar.config.JwtService;
////////////import programmer.belajar.token.RefreshToken;
////////////import programmer.belajar.token.RefreshTokenRepository;
////////////import programmer.belajar.user.Role;
////////////import programmer.belajar.user.User;
////////////import programmer.belajar.user.UserRepository;
////////////
////////////import java.time.LocalDateTime;
////////////
////////////@Service
////////////@RequiredArgsConstructor
////////////public class AuthenticationService {
////////////
////////////    private final UserRepository repository;
////////////    private final PasswordEncoder passwordEncoder;
////////////    private final JwtService jwtService;
////////////    private final AuthenticationManager authenticationManager;
////////////    private final RefreshTokenRepository refreshTokenRepository;
////////////
////////////    // ✅ REGISTER
////////////    public AuthenticationResponse register(RegisterRequest request) {
////////////
////////////        User user = new User();
////////////        user.setEmail(request.getEmail());
////////////        user.setName(request.getFirstname() + " " + request.getLastname());
////////////        user.setPassword(passwordEncoder.encode(request.getPassword()));
////////////        user.setRole(Role.USER);
////////////
////////////        repository.save(user);
////////////
////////////        String accessToken = jwtService.generateAccessToken(user);
////////////        String refreshToken = createRefreshToken(user);
////////////
////////////        return AuthenticationResponse.builder()
////////////                .accessToken(accessToken)
////////////                .refreshToken(refreshToken)
////////////                .build();
////////////    }
////////////
////////////    // ✅ LOGIN
////////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////////////
////////////        authenticationManager.authenticate(
////////////                new UsernamePasswordAuthenticationToken(
////////////                        request.getEmail(),
////////////                        request.getPassword()
////////////                )
////////////        );
////////////
////////////        User user = repository.findByEmail(request.getEmail())
////////////                .orElseThrow(() -> new RuntimeException("User not found"));
////////////
////////////        // 🔥 1 user = 1 refresh token
////////////        refreshTokenRepository.deleteByUserEmail(user.getEmail());
////////////
////////////        String accessToken = jwtService.generateAccessToken(user);
////////////        String refreshToken = createRefreshToken(user);
////////////
////////////        return AuthenticationResponse.builder()
////////////                .accessToken(accessToken)
////////////                .refreshToken(refreshToken)
////////////                .build();
////////////    }
////////////
////////////    // ✅ REFRESH TOKEN LOGIC
////////////    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
////////////
////////////        RefreshToken storedToken = refreshTokenRepository.findByToken(request.getRefreshToken())
////////////                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
////////////
////////////        // 🔥 check expiry
////////////        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
////////////            refreshTokenRepository.delete(storedToken);
////////////            throw new RuntimeException("Refresh token expired");
////////////        }
////////////
////////////        User user = storedToken.getUser();
////////////
////////////        String newAccessToken = jwtService.generateAccessToken(user);
////////////
////////////        return AuthenticationResponse.builder()
////////////                .accessToken(newAccessToken)
////////////                .refreshToken(storedToken.getToken()) // reuse same refresh token
////////////                .build();
////////////    }
////////////
////////////    // ✅ CREATE REFRESH TOKEN
////////////    private String createRefreshToken(User user) {
////////////
////////////        String token = jwtService.generateRefreshToken(user);
////////////
////////////        RefreshToken refreshToken = RefreshToken.builder()
////////////                .token(token)
////////////                .user(user)
////////////                .expiryDate(LocalDateTime.now().plusDays(7))
////////////                .build();
////////////
////////////        refreshTokenRepository.save(refreshToken);
////////////
////////////        return token;
////////////    }
////////////}
//////////
//////////package programmer.belajar.auth;
//////////
//////////import lombok.RequiredArgsConstructor;
//////////import org.springframework.security.authentication.AuthenticationManager;
//////////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////////import org.springframework.security.crypto.password.PasswordEncoder;
//////////import org.springframework.stereotype.Service;
//////////import org.springframework.transaction.annotation.Transactional;
//////////import programmer.belajar.config.JwtService;
//////////import programmer.belajar.token.RefreshToken;
//////////import programmer.belajar.token.RefreshTokenRepository;
//////////import programmer.belajar.user.Role;
//////////import programmer.belajar.user.User;
//////////import programmer.belajar.user.UserRepository;
//////////
//////////import java.time.LocalDateTime;
//////////
//////////@Service
//////////@RequiredArgsConstructor
//////////@Transactional
//////////public class AuthenticationService {
//////////
//////////    private final UserRepository repository;
//////////    private final PasswordEncoder passwordEncoder;
//////////    private final JwtService jwtService;
//////////    private final AuthenticationManager authenticationManager;
//////////    private final RefreshTokenRepository refreshTokenRepository;
//////////
//////////    // =========================
//////////    // ✅ REGISTER
//////////    // =========================
//////////    public AuthenticationResponse register(RegisterRequest request) {
//////////
//////////        User user = new User();
//////////        user.setEmail(request.getEmail());
//////////        user.setName(request.getFirstname() + " " + request.getLastname());
//////////        user.setPassword(passwordEncoder.encode(request.getPassword()));
//////////        user.setRole(Role.USER);
//////////
//////////        repository.save(user);
//////////
//////////        String accessToken = jwtService.generateAccessToken(user);
//////////        String refreshToken = createRefreshToken(user);
//////////
//////////        return AuthenticationResponse.builder()
//////////                .accessToken(accessToken)
//////////                .refreshToken(refreshToken)
//////////                .build();
//////////    }
//////////
//////////    // =========================
//////////    // ✅ LOGIN
//////////    // =========================
//////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//////////
//////////        authenticationManager.authenticate(
//////////                new UsernamePasswordAuthenticationToken(
//////////                        request.getEmail(),
//////////                        request.getPassword()
//////////                )
//////////        );
//////////
//////////        User user = repository.findByEmail(request.getEmail())
//////////                .orElseThrow(() -> new RuntimeException("User not found"));
//////////
//////////        // 🔥 1 user = 1 refresh token (overwrite)
//////////        refreshTokenRepository.deleteByUserEmail(user.getEmail());
//////////
//////////        String accessToken = jwtService.generateAccessToken(user);
//////////        String refreshToken = createRefreshToken(user);
//////////
//////////        return AuthenticationResponse.builder()
//////////                .accessToken(accessToken)
//////////                .refreshToken(refreshToken)
//////////                .build();
//////////    }
//////////
//////////    // =========================
//////////    // 🔥 REFRESH TOKEN (IMPORTANT)
//////////    // =========================
//////////    public AuthenticationResponse refreshToken(String requestToken) {
//////////
//////////        RefreshToken storedToken = refreshTokenRepository.findByToken(requestToken)
//////////                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
//////////
//////////        // ❗ expiry check
//////////        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//////////            throw new RuntimeException("Refresh token expired");
//////////        }
//////////
//////////        User user = storedToken.getUser();
//////////
//////////        // 🔥 ROTATION (DELETE OLD TOKEN)
//////////        refreshTokenRepository.delete(storedToken);
//////////
//////////        // 🔥 GENERATE NEW TOKENS
//////////        String newAccessToken = jwtService.generateAccessToken(user);
//////////        String newRefreshToken = createRefreshToken(user);
//////////
//////////        return AuthenticationResponse.builder()
//////////                .accessToken(newAccessToken)
//////////                .refreshToken(newRefreshToken)
//////////                .build();
//////////    }
//////////
//////////    // =========================
//////////    // 🔧 CREATE REFRESH TOKEN
//////////    // =========================
//////////    private String createRefreshToken(User user) {
//////////
//////////        String token = jwtService.generateRefreshToken(user);
//////////
//////////        RefreshToken refreshToken = RefreshToken.builder()
//////////                .token(token)
//////////                .user(user)
//////////                .expiryDate(LocalDateTime.now().plusDays(7))
//////////                .build();
//////////
//////////        refreshTokenRepository.save(refreshToken);
//////////
//////////        return token;
//////////    }
//////////}
////////
////////package programmer.belajar.auth;
////////
////////import lombok.RequiredArgsConstructor;
////////import org.springframework.security.authentication.*;
////////import org.springframework.security.crypto.password.PasswordEncoder;
////////import org.springframework.stereotype.Service;
////////import org.springframework.transaction.annotation.Transactional;
////////import programmer.belajar.config.JwtService;
////////import programmer.belajar.token.RefreshToken;
////////import programmer.belajar.token.RefreshTokenRepository;
////////import programmer.belajar.user.Role;
////////import programmer.belajar.user.User;
////////import programmer.belajar.user.UserRepository;
////////
////////import java.time.LocalDateTime;
////////
////////@Service
////////@RequiredArgsConstructor
////////@Transactional
////////public class AuthenticationService {
////////
////////    private final UserRepository repository;
////////    private final PasswordEncoder passwordEncoder;
////////    private final JwtService jwtService;
////////    private final AuthenticationManager authenticationManager;
////////    private final RefreshTokenRepository refreshTokenRepository;
////////
////////    public AuthenticationResponse register(RegisterRequest request) {
////////
////////        User user = User.builder()
////////                .email(request.getEmail())
////////                .name(request.getFirstname() + " " + request.getLastname())
////////                .password(passwordEncoder.encode(request.getPassword()))
////////                .role(Role.USER)
////////                .build();
////////
////////        repository.save(user);
////////
////////        String accessToken = jwtService.generateAccessToken(user);
////////        String refreshToken = createRefreshToken(user);
////////
////////        return AuthenticationResponse.builder()
////////                .accessToken(accessToken)
////////                .refreshToken(refreshToken)
////////                .build();
////////    }
////////
////////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////////
////////        authenticationManager.authenticate(
////////                new UsernamePasswordAuthenticationToken(
////////                        request.getEmail(),
////////                        request.getPassword()
////////                )
////////        );
////////
////////        User user = repository.findByEmail(request.getEmail())
////////                .orElseThrow(() -> new RuntimeException("User not found"));
////////
////////        refreshTokenRepository.deleteAllByUser(user);
////////
////////        String accessToken = jwtService.generateAccessToken(user);
////////        String refreshToken = createRefreshToken(user);
////////
////////        return AuthenticationResponse.builder()
////////                .accessToken(accessToken)
////////                .refreshToken(refreshToken)
////////                .build();
////////    }
////////
////////    public AuthenticationResponse refreshToken(String requestToken) {
////////
////////        RefreshToken storedToken = refreshTokenRepository.findByToken(requestToken)
////////                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
////////
////////        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
////////            refreshTokenRepository.delete(storedToken);
////////            throw new RuntimeException("Refresh token expired");
////////        }
////////
////////        User user = storedToken.getUser();
////////
////////        refreshTokenRepository.delete(storedToken);
////////
////////        String newAccessToken = jwtService.generateAccessToken(user);
////////        String newRefreshToken = createRefreshToken(user);
////////
////////        return AuthenticationResponse.builder()
////////                .accessToken(newAccessToken)
////////                .refreshToken(newRefreshToken)
////////                .build();
////////    }
////////
////////    private String createRefreshToken(User user) {
////////
////////        String token = jwtService.generateRefreshToken(user);
////////
////////        RefreshToken refreshToken = RefreshToken.builder()
////////                .token(token)
////////                .user(user)
////////                .expiryDate(LocalDateTime.now().plusDays(7))
////////                .build();
////////
////////        refreshTokenRepository.save(refreshToken);
////////
////////        return token;
////////    }
////////}
//////
//////package programmer.belajar.auth;
//////
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.security.authentication.*;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////import org.springframework.stereotype.Service;
//////import org.springframework.transaction.annotation.Transactional;
//////import programmer.belajar.config.JwtService;
//////import programmer.belajar.token.RefreshToken;
//////import programmer.belajar.token.RefreshTokenRepository;
//////import programmer.belajar.user.Role;
//////import programmer.belajar.user.User;
//////import programmer.belajar.user.UserRepository;
//////
//////import java.time.LocalDateTime;
//////
//////@Service
//////@RequiredArgsConstructor
//////@Transactional
//////public class AuthenticationService {
//////
//////    private final UserRepository repository;
//////    private final PasswordEncoder passwordEncoder;
//////    private final JwtService jwtService;
//////    private final AuthenticationManager authenticationManager;
//////    private final RefreshTokenRepository refreshTokenRepository;
//////
//////    public AuthenticationResponse register(RegisterRequest request) {
//////
//////        User user = User.builder()
//////                .email(request.getEmail())
//////                .name(request.getFirstname() + " " + request.getLastname())
//////                .password(passwordEncoder.encode(request.getPassword()))
//////                .role(Role.USER)
//////                .build();
//////
//////        repository.save(user);
//////
//////        String accessToken = jwtService.generateAccessToken(user);
//////        String refreshToken = createRefreshToken(user);
//////
//////        return AuthenticationResponse.builder()
//////                .accessToken(accessToken)
//////                .refreshToken(refreshToken)
//////                .build();
//////    }
//////
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
//////        refreshTokenRepository.deleteAllByUser(user);
//////
//////        String accessToken = jwtService.generateAccessToken(user);
//////        String refreshToken = createRefreshToken(user);
//////
//////        return AuthenticationResponse.builder()
//////                .accessToken(accessToken)
//////                .refreshToken(refreshToken)
//////                .build();
//////    }
//////
//////    public AuthenticationResponse refreshToken(String requestToken) {
//////
//////        RefreshToken storedToken = refreshTokenRepository.findByToken(requestToken)
//////                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
//////
//////        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//////            refreshTokenRepository.delete(storedToken);
//////            throw new RuntimeException("Refresh token expired");
//////        }
//////
//////        User user = storedToken.getUser();
//////
//////        refreshTokenRepository.delete(storedToken);
//////
//////        String newAccessToken = jwtService.generateAccessToken(user);
//////        String newRefreshToken = createRefreshToken(user);
//////
//////        return AuthenticationResponse.builder()
//////                .accessToken(newAccessToken)
//////                .refreshToken(newRefreshToken)
//////                .build();
//////    }
//////
//////    private String createRefreshToken(User user) {
//////
//////        String token = jwtService.generateRefreshToken(user);
//////
//////        RefreshToken refreshToken = RefreshToken.builder()
//////                .token(token)
//////                .user(user)
//////                .expiryDate(LocalDateTime.now().plusDays(7))
//////                .build();
//////
//////        refreshTokenRepository.save(refreshToken);
//////
//////        return token;
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
////import org.springframework.transaction.annotation.Transactional;
////import programmer.belajar.config.JwtService;
////import programmer.belajar.token.RefreshToken;
////import programmer.belajar.token.RefreshTokenRepository;
////import programmer.belajar.user.Role;
////import programmer.belajar.user.User;
////import programmer.belajar.user.UserRepository;
////
////import java.time.LocalDateTime;
////
////@Service
////@RequiredArgsConstructor
////@Transactional
////public class AuthenticationService {
////
////    private final UserRepository repository;
////    private final PasswordEncoder passwordEncoder;
////    private final JwtService jwtService;
////    private final AuthenticationManager authenticationManager;
////    private final RefreshTokenRepository refreshTokenRepository;
////
////    public AuthenticationResponse register(RegisterRequest request) {
////
////        User user = new User();
////        user.setEmail(request.getEmail());
////        user.setName(request.getFirstname() + " " + request.getLastname());
////        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        user.setRole(Role.USER);
////
////        repository.save(user);
////
////        String accessToken = jwtService.generateAccessToken(user);
////        String refreshToken = createRefreshToken(user);
////
////        return AuthenticationResponse.builder()
////                .accessToken(accessToken)
////                .refreshToken(refreshToken)
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
////                .orElseThrow(() -> new RuntimeException("User not found"));
////
////        // ✅ FIXED HERE
////        refreshTokenRepository.deleteByUserEmail(user.getEmail());
////
////        String accessToken = jwtService.generateAccessToken(user);
////        String refreshToken = createRefreshToken(user);
////
////        return AuthenticationResponse.builder()
////                .accessToken(accessToken)
////                .refreshToken(refreshToken)
////                .build();
////    }
////
////    private String createRefreshToken(User user) {
////
////        String token = jwtService.generateRefreshToken(user);
////
////        RefreshToken refreshToken = RefreshToken.builder()
////                .token(token)
////                .user(user)
////                .expiryDate(LocalDateTime.now().plusDays(7))
////                .build();
////
////        refreshTokenRepository.save(refreshToken);
////
////        return token;
////    }
////}
//
//package programmer.belajar.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import programmer.belajar.config.JwtService;
//import programmer.belajar.token.RefreshToken;
//import programmer.belajar.token.RefreshTokenRepository;
//import programmer.belajar.user.Role;
//import programmer.belajar.user.User;
//import programmer.belajar.user.UserRepository;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class AuthenticationService {
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    // ✅ REGISTER
//    public AuthenticationResponse register(RegisterRequest request) {
//
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setName(request.getFirstname() + " " + request.getLastname());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);
//
//        repository.save(user);
//
//        String accessToken = jwtService.generateAccessToken(user);
//        String refreshToken = createRefreshToken(user);
//
//        return AuthenticationResponse.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
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
//        // 🔥 ONE SESSION ONLY
//        refreshTokenRepository.deleteByUser(user);
//
//        String accessToken = jwtService.generateAccessToken(user);
//        String refreshToken = createRefreshToken(user);
//
//        return AuthenticationResponse.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    // ✅ REFRESH TOKEN (THIS FIXES YOUR ERROR)
//    public AuthenticationResponse refreshToken(String requestToken) {
//
//        RefreshToken storedToken = refreshTokenRepository.findByToken(requestToken)
//                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
//
//        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//            throw new RuntimeException("Refresh token expired");
//        }
//
//        User user = storedToken.getUser();
//
//        // 🔥 ROTATION
//        refreshTokenRepository.delete(storedToken);
//
//        String newAccessToken = jwtService.generateAccessToken(user);
//        String newRefreshToken = createRefreshToken(user);
//
//        return AuthenticationResponse.builder()
//                .accessToken(newAccessToken)
//                .refreshToken(newRefreshToken)
//                .build();
//    }
//
//    // 🔧 CREATE REFRESH TOKEN
//    private String createRefreshToken(User user) {
//
//        String token = jwtService.generateRefreshToken(user);
//
//        RefreshToken refreshToken = RefreshToken.builder()
//                .token(token)
//                .user(user)
//                .expiryDate(LocalDateTime.now().plusDays(7))
//                .build();
//
//        refreshTokenRepository.save(refreshToken);
//
//        return token;
//    }
//}

package programmer.belajar.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmer.belajar.config.JwtService;
import programmer.belajar.token.RefreshToken;
import programmer.belajar.token.RefreshTokenRepository;
import programmer.belajar.user.Role;
import programmer.belajar.user.User;
import programmer.belajar.user.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    // ✅ REGISTER
    public AuthenticationResponse register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getFirstname() + " " + request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        repository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = createRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // ✅ LOGIN
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 IMPORTANT: clear old tokens (1 session policy)
        refreshTokenRepository.deleteByUser(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = createRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // ✅ REFRESH TOKEN
    public AuthenticationResponse refreshToken(String requestToken) {

        RefreshToken storedToken = refreshTokenRepository.findByToken(requestToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        // ❗ expiry check
        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(storedToken);
            throw new RuntimeException("Refresh token expired");
        }

        User user = storedToken.getUser();

        // 🔥 ROTATION (delete old token)
        refreshTokenRepository.delete(storedToken);

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = createRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    // 🔧 CREATE REFRESH TOKEN
    private String createRefreshToken(User user) {

        String token = jwtService.generateRefreshToken(user);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();

        refreshTokenRepository.save(refreshToken);

        return token;
    }
}