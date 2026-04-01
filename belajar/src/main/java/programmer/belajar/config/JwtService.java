//////package programmer.belajar.config;
//////
//////import io.jsonwebtoken.Claims;
//////import io.jsonwebtoken.Jwts;
//////import io.jsonwebtoken.SignatureAlgorithm;
//////import io.jsonwebtoken.io.Decoders;
//////import io.jsonwebtoken.security.Keys;
//////import org.springframework.security.core.userdetails.UserDetails;
//////import org.springframework.stereotype.Service;
//////
//////import javax.xml.crypto.KeySelector;
//////import java.security.Key;
//////import java.util.Date;
//////import java.util.HashMap;
//////import java.util.Map;
//////import java.util.function.Function;
//////
//////@Service
//////
//////public class JwtService {
//////
//////    private static final String SECRET_KEY = "be5325ed7183c76cefaf0a76d11f0b7a02b210f983ac079afc5ed95190865f3a";
//////
//////    public String extractUsername(String token) {
//////        return extractClaim(token, Claims ::getSubject);
//////
//////
//////    }
//////
//////    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//////        final Claims claims = extractALLClaims(token);
//////        return claimsResolver.apply(claims);
//////    }
//////
//////    public String generateToken(UserDetails userDetails) {
//////        return generateToken(new HashMap<>(), userDetails);
//////    }
//////
//////    public String generateToken(
//////            Map<String, Object> extraClaims,
//////            UserDetails userDetails
//////    ){return Jwts
//////        .builder()
//////        .setClaims(extraClaims)
//////        .setSubject(userDetails.getUsername())
//////            .setIssuedAt(new Date(System.currentTimeMillis()))
//////            .setExpiration(new Date(System.currentTimeMillis()+ 1000*60* 24))
//////            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//////            .compact();
//////}
//////    public boolean isTokenValid(String token, UserDetails userDetails) {
//////        final String username = extractUsername(token);
//////        return (username.equals(userDetails.getUsername())) && !isTokenExpired  (token);
//////    }
//////
//////    private boolean isTokenExpired(String token) {
//////        return extractExpiration(token).before(new Date());
//////    }
//////
//////    private Date extractExpiration(String token) {
//////        return extractClaim(token, Claims::getExpiration);
//////    }
//////
//////
//////
//////    private Claims extractALLClaims(String token) {
//////        return Jwts
//////                .parserBuilder()
//////                .setSigningKey(getSignInKey())
//////                .build()
//////                .parseClaimsJws(token)
//////                .getBody();
//////
//////    }
//////
//////    private Key getSignInKey() {
//////        byte[] keyBytes = Decoders.BASE64.decode (SECRET_KEY);
//////        return Keys.hmacShaKeyFor(keyBytes);
//////    }
//////}
////
////
////
////package programmer.belajar.config;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import io.jsonwebtoken.security.Keys;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////import java.security.Key;
////import java.util.Date;
////import java.util.HashMap;
////import java.util.Map;
////import java.util.function.Function;
////
////@Service
////public class JwtService {
////
////    // 🔥 gunakan plain string, JANGAN decode base64
////    private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey12345";
////
////    private Key getSignInKey() {
////        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
////    }
////
////    public String extractUsername(String token) {
////        return extractClaim(token, Claims::getSubject);
////    }
////
////    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
////        final Claims claims = extractAllClaims(token);
////        return resolver.apply(claims);
////    }
////
////    public String generateToken(UserDetails userDetails) {
////        return generateToken(new HashMap<>(), userDetails);
////    }
////
////    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
////        return Jwts.builder()
////                .setClaims(extraClaims)
////                .setSubject(userDetails.getUsername()) // email
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                // 🔥 1 hari (BENAR)
////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
////                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
////
////    public boolean isTokenValid(String token, UserDetails userDetails) {
////        final String username = extractUsername(token);
////        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
////    }
////
////    private boolean isTokenExpired(String token) {
////        return extractExpiration(token).before(new Date());
////    }
////
////    private Date extractExpiration(String token) {
////        return extractClaim(token, Claims::getExpiration);
////    }
////
////    private Claims extractAllClaims(String token) {
////        return Jwts.parserBuilder()
////                .setSigningKey(getSignInKey())
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
////    }
////}
//
//package programmer.belajar.config;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import programmer.belajar.user.User;
//
//import java.security.Key;
//import java.util.*;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    private static final String SECRET_KEY =
//            "mysecretkeymysecretkeymysecretkeymysecretkey1234567890";
//
//    private Key getSignInKey() {
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public String extractRole(String token) {
//        return extractClaim(token, claims -> claims.get("role", String.class));
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
//        final Claims claims = extractAllClaims(token);
//        return resolver.apply(claims);
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//
//        if (userDetails instanceof User user) {
//            extraClaims.put("role", user.getRole().name()); // 🔥 simpan role
//        }
//
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername()) // email
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 hari
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        try {
//            final String username = extractUsername(token);
//            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}

package programmer.belajar.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import programmer.belajar.user.User;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkeymysecretkey1234567890";

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        if (userDetails instanceof User user) {
            claims.put("role", user.getRole().name());
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}