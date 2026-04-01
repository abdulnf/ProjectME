//////package programmer.belajar.config;
//////
//////import jakarta.servlet.FilterChain;
//////import jakarta.servlet.ServletException;
//////import jakarta.servlet.http.HttpServletRequest;
//////import jakarta.servlet.http.HttpServletResponse;
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.lang.NonNull;
//////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////import org.springframework.security.core.context.SecurityContextHolder;
//////import org.springframework.security.core.userdetails.UserDetails;
//////import org.springframework.security.core.userdetails.UserDetailsService;
//////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//////import org.springframework.stereotype.Component;
//////import org.springframework.web.filter.OncePerRequestFilter;
//////
//////import java.io.IOException;
//////
//////
//////@Component
//////@RequiredArgsConstructor
//////public class JwtAuthenticationFilter extends OncePerRequestFilter {
//////
//////    private final JwtService jwtService;
//////    private final UserDetailsService userDetailsService;
//////
//////    @Override
//////    protected void doFilterInternal(
//////            @NonNull HttpServletRequest request,
//////            @NonNull HttpServletResponse response,
//////            @NonNull FilterChain filterChain
//////    ) throws ServletException, IOException {
//////    final String authHeader = request.getHeader("Authorization");
//////    final String jwt;
//////    final String userEmail;
//////    if (authHeader == null || !authHeader.startsWith("Bearer ")){
//////        filterChain.doFilter(request, response);
//////        return;
//////    }
//////
//////
//////    jwt = authHeader.substring(7);
//////    userEmail = jwtService.extractUsername(jwt);
//////
//////        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//////            UserDetails userDetails = this.userDetailsService. loadUserByUsername(userEmail);
//////            if (jwtService.isTokenValid(jwt, userDetails)){
//////                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//////                        userDetails,
//////                        null,
//////                        userDetails.getAuthorities()
//////                );
//////                authToken.setDetails(
//////                        new WebAuthenticationDetailsSource().buildDetails(request)
//////                );
//////                SecurityContextHolder.getContext().setAuthentication(authToken);
//////            }
//////        }
//////        filterChain.doFilter(request,response);
//////    }
//////}
////
////package programmer.belajar.config;
////
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import lombok.RequiredArgsConstructor;
////import org.springframework.lang.NonNull;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
////import org.springframework.stereotype.Component;
////import org.springframework.web.filter.OncePerRequestFilter;
////import java.io.IOException;
////
////@Component
////@RequiredArgsConstructor
////public class JwtAuthenticationFilter extends OncePerRequestFilter {
////
////    private final JwtService jwtService;
////    private final UserDetailsService userDetailsService;
////
////    @Override
////    protected void doFilterInternal(
////            @NonNull HttpServletRequest request,
////            @NonNull HttpServletResponse response,
////            @NonNull FilterChain filterChain
////    ) throws ServletException, IOException {
////
////        // ✅ WAJIB: bypass endpoint auth
////        if (request.getServletPath().startsWith("/api/v1/auth")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        final String authHeader = request.getHeader("Authorization");
////        final String jwt;
////        final String username;
////
////        // ✅ Jangan paksa ada token
////        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        jwt = authHeader.substring(7);
////
////        try {
////            username = jwtService.extractUsername(jwt);
////        } catch (Exception e) {
////            // ❗ Jangan blok request kalau token invalid
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        // ✅ Set authentication kalau valid
////        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
////
////            if (jwtService.isTokenValid(jwt, userDetails)) {
////                UsernamePasswordAuthenticationToken authToken =
////                        new UsernamePasswordAuthenticationToken(
////                                userDetails,
////                                null,
////                                userDetails.getAuthorities()
////                        );
////
////                authToken.setDetails(
////                        new WebAuthenticationDetailsSource().buildDetails(request)
////                );
////
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }
////        }
////
////        filterChain.doFilter(request, response);
////    }
////}
//
//package programmer.belajar.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        // ✅ bypass endpoint auth
//        if (request.getServletPath().startsWith("/api/v1/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String authHeader = request.getHeader("Authorization");
//
//        // ✅ tidak ada token → lanjut
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String jwt = authHeader.substring(7);
//        final String email;
//
//        try {
//            email = jwtService.extractUsername(jwt); // 🔥 ini email
//        } catch (Exception e) {
//            // ⚠️ minimal logging
//            System.out.println("JWT error: " + e.getMessage());
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // ✅ validasi & set authentication
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
//

package programmer.belajar.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getServletPath().startsWith("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String email;

        try {
            email = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}