//////////////package programmer.belajar.entity;
//////////////
//////////////import jakarta.persistence.*;
//////////////import lombok.AllArgsConstructor;
//////////////import lombok.Getter;
//////////////import lombok.NoArgsConstructor;
//////////////import lombok.Setter;
//////////////import programmer.belajar.user.Address;
//////////////import programmer.belajar.user.User;
//////////////
//////////////import java.util.List;
//////////////
//////////////@Getter
//////////////@Setter
//////////////@AllArgsConstructor
//////////////@NoArgsConstructor
//////////////@Entity
//////////////@Table(name = "contacts")
//////////////
//////////////public class Contact {
//////////////@Id
//////////////    private String id;
//////////////
//////////////    @Column(name = "first_name")
//////////////
//////////////    private String firstname;
//////////////
//////////////    @Column(name = "last_name")
//////////////
//////////////    private String lastname;
//////////////
//////////////
//////////////    private String phone;
//////////////
//////////////
//////////////
//////////////    private String email;
//////////////
//////////////    @ManyToOne
//////////////    @JoinColumn(name = "username", referencedColumnName = "username")
//////////////    private User user;
//////////////
//////////////    @OneToMany(mappedBy = "contact")
//////////////    private List<Address> addresses ;
//////////////}
////////////
////////////package programmer.belajar.entity;
////////////
////////////import jakarta.persistence.*;
////////////import lombok.*;
////////////import programmer.belajar.user.Address;
////////////import programmer.belajar.user.User;
////////////
////////////import java.util.List;
////////////
////////////@Getter
////////////@Setter
////////////@AllArgsConstructor
////////////@NoArgsConstructor
////////////@Entity
////////////@Table(name = "contacts")
////////////public class Contact {
////////////
////////////    @Id
////////////    private String id;
////////////
////////////    @Column(name = "first_name")
////////////    private String firstname;
////////////
////////////    @Column(name = "last_name")
////////////    private String lastname;
////////////
////////////    private String phone;
////////////
////////////    private String email; // email contact (AMAN)
////////////
////////////    @ManyToOne
////////////    @JoinColumn(name = "user_email")
////////////    private User user;
////////////
////////////    @OneToMany(mappedBy = "contact")
////////////    private List<Address> addresses;
////////////}
//////////
//////////package programmer.belajar.entity;
//////////
//////////import com.fasterxml.jackson.annotation.JsonIgnore;
//////////import jakarta.persistence.*;
//////////import lombok.*;
//////////import programmer.belajar.user.Address;
//////////import programmer.belajar.user.User;
//////////
//////////import java.util.List;
//////////
//////////@Getter
//////////@Setter
//////////@AllArgsConstructor
//////////@NoArgsConstructor
//////////@Entity
//////////@Table(name = "contacts")
//////////public class Contact {
//////////
//////////    @Id
//////////    private String id;
//////////
//////////    @Column(name = "first_name")
//////////    private String firstname;
//////////
//////////    @Column(name = "last_name")
//////////    private String lastname;
//////////
//////////    private String phone;
//////////
//////////    private String email;
//////////
//////////    @ManyToOne
//////////    @JoinColumn(name = "user_email")
//////////    @JsonIgnore // 🔥 PUTUS LOOP ke User
//////////    private User user;
//////////
//////////    @OneToMany(mappedBy = "contact")
//////////    @JsonIgnore // 🔥 PUTUS LOOP ke Address
//////////    private List<Address> addresses;
//////////}
////////
////////package programmer.belajar.entity;
////////
////////import com.fasterxml.jackson.annotation.JsonIgnore;
////////import jakarta.persistence.*;
////////import lombok.*;
////////import programmer.belajar.user.Address;
////////import programmer.belajar.user.User;
////////
////////import java.util.List;
////////
////////@Getter
////////@Setter
////////@AllArgsConstructor
////////@NoArgsConstructor
////////@Entity
////////@Table(name = "contacts")
////////@ToString(exclude = {"user", "addresses"}) // 🔥 cegah loop toString
////////@EqualsAndHashCode(exclude = {"user", "addresses"}) // 🔥 cegah loop equals
////////public class Contact {
////////
////////    @Id
////////    private String id;
////////
////////    @Column(name = "first_name")
////////    private String firstname;
////////
////////    @Column(name = "last_name")
////////    private String lastname;
////////
////////    private String phone;
////////
////////    private String email;
////////
////////    @ManyToOne
////////    @JoinColumn(name = "user_email")
////////    @JsonIgnore // 🔥 cegah loop JSON
////////    private User user;
////////
////////    @OneToMany(mappedBy = "contact")
////////    @JsonIgnore // 🔥 cegah loop JSON
////////    private List<Address> addresses;
////////}
//////
//////package programmer.belajar.entity;
//////
//////import jakarta.persistence.*;
//////import lombok.*;
//////import programmer.belajar.user.Address;
//////import programmer.belajar.user.User;
//////
//////import java.util.List;
//////
//////@Getter
//////@Setter
//////@AllArgsConstructor
//////@NoArgsConstructor
//////@Entity
//////@Table(name = "contacts")
//////public class Contact {
//////
//////    @Id
//////    private String id;
//////
//////    @Column(name = "first_name")
//////    private String firstname;
//////
//////    @Column(name = "last_name")
//////    private String lastname;
//////
//////    private String phone;
//////
//////    private String email;
//////
//////    @ManyToOne
//////    @JoinColumn(name = "user_email")
//////    private User user;
//////
//////    @OneToMany(mappedBy = "contact")
//////    @com.fasterxml.jackson.annotation.JsonIgnore // 🔥 WAJIB
//////    private List<Address> addresses;
//////}
////
////package programmer.belajar.entity;
////
////import com.fasterxml.jackson.annotation.JsonIgnore;
////import jakarta.persistence.*;
////import lombok.*;
////import programmer.belajar.user.Address;
////import programmer.belajar.user.User;
////
////import java.util.List;
////
////@Getter
////@Setter
////@AllArgsConstructor
////@NoArgsConstructor
////@Entity
////@Table(name = "contacts")
////@ToString(exclude = {"user", "addresses"})
////@EqualsAndHashCode(exclude = {"user", "addresses"})
////public class Contact {
////
////    @Id
////    private String id;
////
////    @Column(name = "first_name")
////    private String firstname;
////
////    @Column(name = "last_name")
////    private String lastname;
////
////    private String phone;
////
////    private String email;
////
////    @ManyToOne
////    @JoinColumn(name = "user_email")
////    @JsonIgnore // 🔥 INI YANG HILANG
////    private User user;
////
////    @OneToMany(mappedBy = "contact")
////    @JsonIgnore
////    private List<Address> addresses;
////}
//
//package programmer.belajar.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.*;
//import programmer.belajar.user.Address;
//import programmer.belajar.user.User;
//
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "contacts")
//@ToString(exclude = {"user", "addresses"})
//@EqualsAndHashCode(exclude = {"user", "addresses"})
//public class Contact {
//
//    @Id
//    private String id;
//
//    @Column(name = "first_name")
//    private String firstname;
//
//    @Column(name = "last_name")
//    private String lastname;
//
//    private String phone;
//
//    private String email;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_email", nullable = false)
//    @JsonIgnore
//    private User user;
//
//    @OneToMany(mappedBy = "contact")
//    @JsonIgnore
//    private List<Address> addresses;
//}

package programmer.belajar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import programmer.belajar.user.Address;
import programmer.belajar.user.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    private String id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_email")
    @JsonIgnore // 🔥 penting
    private User user;

    @OneToMany(mappedBy = "contact")
    @JsonIgnore
    private List<Address> addresses;
}