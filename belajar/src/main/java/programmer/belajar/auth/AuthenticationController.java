//////////package programmer.belajar.auth;
//////////
//////////import lombok.RequiredArgsConstructor;
//////////import org.springframework.http.ResponseEntity;
//////////import org.springframework.web.bind.annotation.*;
//////////
//////////@RestController
//////////@RequestMapping("/api/v1/auth")
//////////@RequiredArgsConstructor
//////////public class AuthenticationController {
//////////
//////////    private final AuthenticationService service;
//////////
//////////    // =========================
//////////    // ✅ REGISTER
//////////    // =========================
//////////    @PostMapping("/register")
//////////    public ResponseEntity<AuthenticationResponse> register(
//////////            @RequestBody RegisterRequest request
//////////    ) {
//////////        return ResponseEntity.ok(service.register(request));
//////////    }
//////////
//////////    // =========================
//////////    // ✅ LOGIN / AUTHENTICATE
//////////    // =========================
//////////    @PostMapping("/authenticate")
//////////    public ResponseEntity<AuthenticationResponse> authenticate(
//////////            @RequestBody AuthenticationRequest request
//////////    ) {
//////////        return ResponseEntity.ok(service.authenticate(request));
//////////    }
//////////
//////////    // =========================
//////////    // 🔥 REFRESH TOKEN (CORRECT WAY)
//////////    // =========================
//////////    @PostMapping("/refresh")
//////////    public ResponseEntity<AuthenticationResponse> refreshToken(
//////////            @RequestBody RefreshTokenRequest request
//////////    ) {
//////////        return ResponseEntity.ok(
//////////                service.refreshToken(request.getRefreshToken())
//////////        );
//////////    }
//////////}
////////
////////package programmer.belajar.auth;
////////
////////import lombok.RequiredArgsConstructor;
////////import org.springframework.http.ResponseEntity;
////////import org.springframework.web.bind.annotation.*;
////////import programmer.belajar.config.JwtService;
////////import programmer.belajar.token.RefreshToken;
////////import programmer.belajar.token.RefreshTokenRepository;
////////import programmer.belajar.user.User;
////////
////////import java.time.LocalDateTime;
////////
////////@RestController
////////@RequestMapping("/api/v1/auth")
////////@RequiredArgsConstructor
////////public class AuthenticationController {
////////
////////    private final AuthenticationService service;
////////    private final RefreshTokenRepository refreshTokenRepository;
////////    private final JwtService jwtService;
////////
////////    // ✅ REGISTER
////////    @PostMapping("/register")
////////    public ResponseEntity<AuthenticationResponse> register(
////////            @RequestBody RegisterRequest request
////////    ) {
////////        return ResponseEntity.ok(service.register(request));
////////    }
////////
////////    // ✅ LOGIN
////////    @PostMapping("/authenticate")
////////    public ResponseEntity<AuthenticationResponse> authenticate(
////////            @RequestBody AuthenticationRequest request
////////    ) {
////////        return ResponseEntity.ok(service.authenticate(request));
////////    }
////////
////////    // 🔥 REFRESH TOKEN (UPDATED - ROTATION LOGIC)
////////    @PostMapping("/refresh")
////////    public ResponseEntity<AuthenticationResponse> refreshToken(
////////            @RequestBody RefreshTokenRequest request
////////    ) {
////////
////////        // 1. Find token in DB
////////        RefreshToken oldToken = refreshTokenRepository.findByToken(request.getRefreshToken())
////////                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
////////
////////        // 2. Check expiry
////////        if (oldToken.getExpiryDate().isBefore(LocalDateTime.now())) {
////////            throw new RuntimeException("Refresh token expired");
////////        }
////////
////////        User user = oldToken.getUser();
////////
////////        // 3. DELETE old token (rotation)
////////        refreshTokenRepository.delete(oldToken);
////////
////////        // 4. Generate new tokens
////////        String newAccessToken = jwtService.generateAccessToken(user);
////////        String newRefreshToken = jwtService.generateRefreshToken(user);
////////
////////        // 5. Save new refresh token
////////        RefreshToken newToken = RefreshToken.builder()
////////                .token(newRefreshToken)
////////                .user(user)
////////                .expiryDate(LocalDateTime.now().plusDays(7))
////////                .build();
////////
////////        refreshTokenRepository.save(newToken);
////////
////////        // 6. Return response
////////        return ResponseEntity.ok(
////////                AuthenticationResponse.builder()
////////                        .accessToken(newAccessToken)
////////                        .refreshToken(newRefreshToken)
////////                        .build()
////////        );
////////    }
////////}
//////
//////package programmer.belajar.auth;
//////
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.web.bind.annotation.*;
//////
//////@RestController
//////@RequestMapping("/api/v1/auth")
//////@RequiredArgsConstructor
//////public class AuthenticationController {
//////
//////    private final AuthenticationService service;
//////
//////    @PostMapping("/register")
//////    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//////        return ResponseEntity.ok(service.register(request));
//////    }
//////
//////    @PostMapping("/login")
//////    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
//////        try {
//////            return ResponseEntity.ok(service.authenticate(request));
//////        } catch (Exception e) {
//////            return ResponseEntity.status(401).body("Invalid email or password");
//////        }
//////    }
//////
//////    @PostMapping("/refresh")
//////    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
//////        try {
//////            return ResponseEntity.ok(service.refreshToken(request.getRefreshToken()));
//////        } catch (Exception e) {
//////            return ResponseEntity.status(401).body(e.getMessage());
//////        }
//////    }
//////}
////
////package programmer.belajar.auth;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api/v1/auth")
////@RequiredArgsConstructor
////public class AuthenticationController {
////
////    private final AuthenticationService service;
////
////    @PostMapping("/register")
////    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
////        return ResponseEntity.ok(service.register(request));
////    }
////
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
////        return ResponseEntity.ok(service.authenticate(request));
////    }
////
////    @PostMapping("/refresh")
////    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
////        return ResponseEntity.ok(service.refreshToken(request.getRefreshToken()));
////    }
////}
//
//package programmer.belajar.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService service;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//
//    @PostMapping("/refresh")
//    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
//        return ResponseEntity.ok(
//                service.refreshToken(request.getRefreshToken())
//        );
//    }
//}

package programmer.belajar.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(
                service.refreshToken(request.getRefreshToken())
        );
    }
}