//////////////package programmer.belajar.user;
//////////////
//////////////import jakarta.persistence.*;
//////////////import lombok.*;
//////////////import org.springframework.security.core.GrantedAuthority;
//////////////import org.springframework.security.core.authority.SimpleGrantedAuthority;
//////////////import org.springframework.security.core.userdetails.UserDetails;
//////////////import java.util.Collection;
//////////////import java.util.List;
//////////////
//////////////@Getter
//////////////@Setter
////////////////@Data
//////////////@Builder
//////////////@AllArgsConstructor
//////////////@NoArgsConstructor
//////////////@Entity
//////////////@Table(name = "users")
//////////////public class User implements UserDetails {
//////////////
//////////////    @Id
//////////////    private String username;
//////////////
//////////////    private String password;
//////////////
//////////////    private String name;
//////////////
//////////////    private String token;
//////////////
//////////////    private String email;
//////////////
//////////////    @Column(name = "token_expired_at")
//////////////    private Long tokenExpiredAt;
//////////////
//////////////    @Enumerated(EnumType.STRING)
//////////////    private Role role;
//////////////
//////////////    @Override
//////////////    public Collection<? extends GrantedAuthority> getAuthorities() {
//////////////        return List.of(new SimpleGrantedAuthority(role.name()));
//////////////    }
//////////////
//////////////    @Override public String getUsername() { return username; }
//////////////    @Override public boolean isAccountNonExpired() { return true; }
//////////////    @Override public boolean isAccountNonLocked() { return true; }
//////////////    @Override public boolean isCredentialsNonExpired() { return true; }
//////////////    @Override public boolean isEnabled() { return true; }
//////////////}
////////////
////////////
////////////package programmer.belajar.user;
////////////
////////////import jakarta.persistence.*;
////////////import lombok.*;
////////////import org.springframework.security.core.GrantedAuthority;
////////////import org.springframework.security.core.userdetails.UserDetails;
////////////import java.util.Collection;
////////////import java.util.List;
////////////
////////////@Getter
////////////@Setter
////////////@Builder
////////////@AllArgsConstructor
////////////@NoArgsConstructor
////////////@Entity
////////////@Table(name = "users")
////////////public class User implements UserDetails {
////////////
////////////    @Id
////////////    private String email; // 🔥 jadi primary key
////////////
////////////    private String password;
////////////
////////////    private String name;
////////////
////////////    @Enumerated(EnumType.STRING)
////////////    private Role role;
////////////
////////////    @Override
////////////    public Collection<? extends GrantedAuthority> getAuthorities() {
////////////        return List.of(() -> role.name());
////////////    }
////////////
////////////    @Override
////////////    public String getUsername() {
////////////        return email; // 🔥 Spring Security pakai ini
////////////    }
////////////
////////////    @Override public boolean isAccountNonExpired() { return true; }
////////////    @Override public boolean isAccountNonLocked() { return true; }
////////////    @Override public boolean isCredentialsNonExpired() { return true; }
////////////    @Override public boolean isEnabled() { return true; }
////////////}
//////////
//////////
//////////package programmer.belajar.user;
//////////
//////////import jakarta.persistence.*;
//////////import lombok.*;
//////////import org.springframework.security.core.GrantedAuthority;
//////////import org.springframework.security.core.authority.SimpleGrantedAuthority;
//////////import org.springframework.security.core.userdetails.UserDetails;
//////////
//////////import java.util.Collection;
//////////import java.util.List;
//////////
//////////@Getter
//////////@Setter
//////////@Builder
//////////@AllArgsConstructor
//////////@NoArgsConstructor
//////////@Entity
//////////@Table(name = "users")
//////////public class User implements UserDetails {
//////////
//////////    @Id
//////////    private String email; // ✅ jadi primary key & login
//////////
//////////    private String password;
//////////
//////////    private String name;
//////////
//////////    @Enumerated(EnumType.STRING)
//////////    private Role role;
//////////
//////////    @Override
//////////    public Collection<? extends GrantedAuthority> getAuthorities() {
//////////        return List.of(new SimpleGrantedAuthority(role.name())); // ✅ lebih aman
//////////    }
//////////
//////////    @Override
//////////    public String getUsername() {
//////////        return email; // ✅ Spring Security pakai ini
//////////    }
//////////
//////////    @Override public boolean isAccountNonExpired() { return true; }
//////////    @Override public boolean isAccountNonLocked() { return true; }
//////////    @Override public boolean isCredentialsNonExpired() { return true; }
//////////    @Override public boolean isEnabled() { return true; }
//////////}
////////
////////
////////package programmer.belajar.user;
////////
////////import jakarta.persistence.*;
////////import lombok.*;
////////import org.springframework.security.core.GrantedAuthority;
////////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////////import org.springframework.security.core.userdetails.UserDetails;
////////
////////import java.util.Collection;
////////import java.util.List;
////////
////////@Getter
////////@Setter
////////@Builder
////////@AllArgsConstructor
////////@NoArgsConstructor
////////@Entity
////////@Table(name = "users")
////////public class User implements UserDetails {
////////
////////    @Id
////////    private String email;
////////
////////    private String password;
////////
////////    private String name;
////////
////////    @Enumerated(EnumType.STRING)
////////    private Role role;
////////
////////    @Override
////////    public Collection<? extends GrantedAuthority> getAuthorities() {
////////        return List.of(new SimpleGrantedAuthority(role.name()));
////////    }
////////
////////    @Override
////////    public String getUsername() {
////////        return email;
////////    }
////////
////////    @Override public boolean isAccountNonExpired() { return true; }
////////    @Override public boolean isAccountNonLocked() { return true; }
////////    @Override public boolean isCredentialsNonExpired() { return true; }
////////    @Override public boolean isEnabled() { return true; }
////////}
//////
//////
//////package programmer.belajar.user;
//////
//////import jakarta.persistence.*;
//////import lombok.*;
//////import org.springframework.security.core.GrantedAuthority;
//////import org.springframework.security.core.authority.SimpleGrantedAuthority;
//////import org.springframework.security.core.userdetails.UserDetails;
//////
//////import java.util.Collection;
//////import java.util.List;
//////
//////@Getter
//////@Setter
//////@Builder
//////@AllArgsConstructor
//////@NoArgsConstructor
//////@Entity
//////@Table(name = "users")
//////public class User implements UserDetails {
//////
//////    @Id
//////    private String email;
//////
//////    private String password;
//////
//////    private String name;
//////
//////    @Enumerated(EnumType.STRING)
//////    private Role role;
//////
//////    @Override
//////    public Collection<? extends GrantedAuthority> getAuthorities() {
//////        return List.of(new SimpleGrantedAuthority(role.name()));
//////    }
//////
//////    @Override
//////    public String getUsername() {
//////        return email;
//////    }
//////
//////    @Override public boolean isAccountNonExpired() { return true; }
//////    @Override public boolean isAccountNonLocked() { return true; }
//////    @Override public boolean isCredentialsNonExpired() { return true; }
//////    @Override public boolean isEnabled() { return true; }
//////}
////
////package programmer.belajar.user;
////
////import jakarta.persistence.*;
////import lombok.*;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////
////import java.util.Collection;
////import java.util.List;
////
////@Getter
////@Setter
////@Builder
////@AllArgsConstructor
////@NoArgsConstructor
////@Entity
////@Table(name = "users")
////@ToString(exclude = {}) // aman (tidak ada relasi)
////@EqualsAndHashCode(exclude = {})
////public class User implements UserDetails {
////
////    @Id
////    private String email;
////
////    private String password;
////
////    private String name;
////
////    @Enumerated(EnumType.STRING)
////    private Role role;
////
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return List.of(new SimpleGrantedAuthority(role.name()));
////    }
////
////    @Override
////    public String getUsername() {
////        return email;
////    }
////
////    @Override public boolean isAccountNonExpired() { return true; }
////    @Override public boolean isAccountNonLocked() { return true; }
////    @Override public boolean isCredentialsNonExpired() { return true; }
////    @Override public boolean isEnabled() { return true; }
////}
//
//
//package programmer.belajar.user;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "users")
//public class User implements UserDetails {
//
//    @Id
//    private String email;
//
//    private String password;
//
//    private String name;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override public boolean isAccountNonExpired() { return true; }
//    @Override public boolean isAccountNonLocked() { return true; }
//    @Override public boolean isCredentialsNonExpired() { return true; }
//    @Override public boolean isEnabled() { return true; }
//}

package programmer.belajar.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String email;

    @JsonIgnore
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override @JsonIgnore public boolean isAccountNonExpired() { return true; }
    @Override @JsonIgnore public boolean isAccountNonLocked() { return true; }
    @Override @JsonIgnore public boolean isCredentialsNonExpired() { return true; }
    @Override @JsonIgnore public boolean isEnabled() { return true; }
}