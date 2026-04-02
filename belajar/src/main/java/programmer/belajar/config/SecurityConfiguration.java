////////////////package programmer.belajar.config;
////////////////
////////////////import lombok.RequiredArgsConstructor;
////////////////import org.springframework.context.annotation.Bean;
////////////////import org.springframework.context.annotation.Configuration;
////////////////import org.springframework.security.authentication.AuthenticationProvider;
////////////////import org.springframework.security.config.Customizer;
////////////////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////////////////import org.springframework.security.config.http.SessionCreationPolicy;
////////////////import org.springframework.security.core.Authentication;
////////////////import org.springframework.security.web.SecurityFilterChain;
////////////////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////////////////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////////////////
////////////////@Configuration
////////////////@EnableWebSecurity
////////////////@RequiredArgsConstructor
////////////////
////////////////public class SecurityConfiguration {
////////////////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////////////////    private final AuthenticationProvider authenticationProvider;
////////////////
////////////////
//////////////////    @Bean
//////////////////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////////////////        http
//////////////////                .csrf(csrf -> csrf.disable())
//////////////////                .authorizeHttpRequests(
//////////////////                        auth ->
//////////////////                                auth.requestMatchers("/api/v1/auth/**")
//////////////////                                .permitAll()
//////////////////                                .anyRequest()
//////////////////                                .authenticated()
//////////////////                )
//////////////////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//////////////////                .authenticationProvider(authenticationProvider)
//////////////////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//////////////////
//////////////////        return http.build();
//////////////////    }
////////////////
////////////////    @Bean
////////////////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////////////////        http
////////////////                .csrf(csrf -> csrf.disable())
////////////////                .cors(cors -> cors.configurationSource(request -> {
////////////////                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
////////////////                    corsConfig.addAllowedOrigin("*");
////////////////                    corsConfig.addAllowedMethod("*");
////////////////                    corsConfig.addAllowedHeader("*");
////////////////                    return corsConfig;
////////////////                }))
////////////////                .authorizeHttpRequests(auth -> auth
////////////////                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/v1/auth/**").permitAll()
////////////////                        .requestMatchers("/api/v1/auth/**").permitAll()
////////////////                        .anyRequest().authenticated()
////////////////                )
////////////////                .sessionManagement(session ->
////////////////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////////////////                )
////////////////                .authenticationProvider(authenticationProvider)
////////////////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////////////////
////////////////        return http.build();
////////////////    }
////////////////}
//////////////
//////////////package programmer.belajar.config;
//////////////
//////////////import lombok.RequiredArgsConstructor;
//////////////import org.springframework.context.annotation.Bean;
//////////////import org.springframework.context.annotation.Configuration;
//////////////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////////////import org.springframework.security.config.http.SessionCreationPolicy;
//////////////import org.springframework.security.web.SecurityFilterChain;
//////////////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////////////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////////////
//////////////@Configuration
//////////////@EnableWebSecurity
//////////////@RequiredArgsConstructor
//////////////public class SecurityConfiguration {
//////////////
//////////////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//////////////
//////////////    @Bean
//////////////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////////////        http
//////////////                .csrf(csrf -> csrf.disable())
//////////////                .authorizeHttpRequests(auth -> auth
//////////////                        .requestMatchers("/api/v1/auth/**").permitAll()
//////////////                        .anyRequest().authenticated()
//////////////                )
//////////////                .sessionManagement(session ->
//////////////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//////////////                )
//////////////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//////////////
//////////////        return http.build();
//////////////    }
//////////////}
////////////
////////////package programmer.belajar.config;
////////////
////////////import lombok.RequiredArgsConstructor;
////////////import org.springframework.context.annotation.Bean;
////////////import org.springframework.context.annotation.Configuration;
////////////import org.springframework.security.authentication.AuthenticationProvider;
////////////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////////////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////////////import org.springframework.security.config.http.SessionCreationPolicy;
////////////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////////////import org.springframework.security.crypto.password.PasswordEncoder;
////////////import org.springframework.security.web.SecurityFilterChain;
////////////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////////////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////////////import programmer.belajar.service.CustomUserDetailsService;
////////////
////////////@Configuration
////////////@EnableWebSecurity
////////////@RequiredArgsConstructor
////////////public class SecurityConfiguration {
////////////
////////////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////////////    private final CustomUserDetailsService userDetailsService;
////////////
////////////    @Bean
////////////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////////////        http
////////////                .csrf(csrf -> csrf.disable())
////////////                .authorizeHttpRequests(auth -> auth
////////////                        .requestMatchers("/api/v1/auth/**").permitAll()
////////////                        .anyRequest().authenticated()
////////////                )
////////////                .sessionManagement(session ->
////////////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////////////                )
////////////                .authenticationProvider(authenticationProvider())
////////////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////////////
////////////        return http.build();
////////////    }
////////////
////////////    @Bean
////////////    public AuthenticationProvider authenticationProvider() {
////////////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////////////        provider.setUserDetailsService(userDetailsService);
////////////        provider.setPasswordEncoder(passwordEncoder());
////////////        return provider;
////////////    }
////////////
////////////    @Bean
////////////    public PasswordEncoder passwordEncoder() {
////////////        return new BCryptPasswordEncoder();
////////////    }
////////////}
//////////
//////////package programmer.belajar.config;
//////////
//////////import lombok.RequiredArgsConstructor;
//////////import org.springframework.context.annotation.Bean;
//////////import org.springframework.context.annotation.Configuration;
//////////import org.springframework.security.authentication.AuthenticationManager;
//////////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//////////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////////import org.springframework.security.config.http.SessionCreationPolicy;
//////////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////////import org.springframework.security.crypto.password.PasswordEncoder;
//////////import org.springframework.security.web.SecurityFilterChain;
//////////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////////
//////////@Configuration
//////////@EnableWebSecurity
//////////@RequiredArgsConstructor
//////////public class SecurityConfiguration {
//////////
//////////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//////////
//////////    @Bean
//////////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////////        http
//////////                .csrf(csrf -> csrf.disable())
//////////                .authorizeHttpRequests(auth -> auth
//////////                        .requestMatchers("/api/v1/auth/**").permitAll()
//////////                        .anyRequest().authenticated()
//////////                )
//////////                .sessionManagement(session ->
//////////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//////////                )
//////////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//////////
//////////        return http.build();
//////////    }
//////////
//////////    @Bean
//////////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//////////        return config.getAuthenticationManager();
//////////    }
//////////
//////////    @Bean
//////////    public PasswordEncoder passwordEncoder() {
//////////        return new BCryptPasswordEncoder();
//////////    }
//////////}
////////package programmer.belajar.config;
////////
////////import lombok.RequiredArgsConstructor;
////////import org.springframework.context.annotation.Bean;
////////import org.springframework.context.annotation.Configuration;
////////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////////import org.springframework.security.config.http.SessionCreationPolicy;
////////import org.springframework.security.web.SecurityFilterChain;
////////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////////
////////@Configuration
////////@EnableWebSecurity
////////@RequiredArgsConstructor
////////public class SecurityConfiguration {
////////
////////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////////
////////    @Bean
////////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////////        http
////////                .csrf(csrf -> csrf.disable())
////////                .authorizeHttpRequests(auth -> auth
////////                        .requestMatchers("/api/v1/auth/**").permitAll()
////////                        .anyRequest().authenticated()
////////                )
////////                .sessionManagement(session ->
////////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////////                )
////////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////////
////////        return http.build();
////////    }
////////}
//////package programmer.belajar.config;
//////
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.config.http.SessionCreationPolicy;
//////import org.springframework.security.web.SecurityFilterChain;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////
//////@Configuration
//////@EnableWebSecurity
//////@RequiredArgsConstructor
//////public class SecurityConfiguration {
//////
//////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//////
//////    @Bean
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////        http
//////                .csrf(csrf -> csrf.disable())
//////                .authorizeHttpRequests(auth -> auth
//////                        .requestMatchers("/api/v1/auth/**").permitAll()
//////                        .anyRequest().authenticated()
//////                )
//////                .sessionManagement(session ->
//////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//////                )
//////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//////
//////        return http.build();
//////    }
//////}
////
////package programmer.belajar.config;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////@RequiredArgsConstructor
////public class SecurityConfiguration {
////
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/v1/auth/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .sessionManagement(session ->
////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                )
////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////}

package programmer.belajar.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}



