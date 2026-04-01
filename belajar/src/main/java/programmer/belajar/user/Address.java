//////////package programmer.belajar.user ;
//////////
//////////import jakarta.persistence.*;
//////////import programmer.belajar.entity.Contact;
//////////import lombok.AllArgsConstructor;
//////////import lombok.Getter;
//////////import lombok.NoArgsConstructor;
//////////import lombok.Setter;
//////////
//////////@Getter
//////////@Setter
//////////@AllArgsConstructor
//////////@NoArgsConstructor
//////////@Entity
//////////@Table(name = "addresses")
//////////public class Address {
//////////    @Id
//////////    private String id;
//////////
//////////    private String street;
//////////
//////////    private String city;
//////////
//////////    private String province;
//////////
//////////    private String country;
//////////
//////////    @Column(name = "postal_code")
//////////    private String postalCode;
//////////
//////////    @ManyToOne
//////////    @JoinColumn(name = "contact_id", referencedColumnName = "id")
//////////    private Contact contact;
//////////}
////////
////////
////////package programmer.belajar.user;
////////
////////import jakarta.persistence.*;
////////import lombok.*;
////////import programmer.belajar.entity.Contact;
////////
////////@Getter
////////@Setter
////////@AllArgsConstructor
////////@NoArgsConstructor
////////@Entity
////////@Table(name = "addresses")
////////public class Address {
////////
////////    @Id
////////    private String id;
////////
////////    private String street;
////////    private String city;
////////    private String province;
////////    private String country;
////////
////////    @Column(name = "postal_code")
////////    private String postalCode;
////////
////////    @ManyToOne
////////    @JoinColumn(name = "contact_id") // ✅ tidak perlu referencedColumnName
////////    private Contact contact;
////////}
////////
//////
//////
//////package programmer.belajar.user;
//////
//////import com.fasterxml.jackson.annotation.JsonIgnore;
//////import jakarta.persistence.*;
//////import lombok.*;
//////import programmer.belajar.entity.Contact;
//////
//////@Getter
//////@Setter
//////@AllArgsConstructor
//////@NoArgsConstructor
//////@Entity
//////@Table(name = "addresses")
//////public class Address {
//////
//////    @Id
//////    private String id;
//////
//////    private String street;
//////    private String city;
//////    private String province;
//////    private String country;
//////
//////    @Column(name = "postal_code")
//////    private String postalCode;
//////
//////    @ManyToOne
//////    @JoinColumn(name = "contact_id")
//////    @JsonIgnore // 🔥 PUTUS LOOP ke Contact
//////    private Contact contact;
//////}
////
////package programmer.belajar.user;
////
////import com.fasterxml.jackson.annotation.JsonIgnore;
////import jakarta.persistence.*;
////import lombok.*;
////import programmer.belajar.entity.Contact;
////
////@Getter
////@Setter
////@AllArgsConstructor
////@NoArgsConstructor
////@Entity
////@Table(name = "addresses")
////@ToString(exclude = {"contact"}) // 🔥 cegah loop toString
////@EqualsAndHashCode(exclude = {"contact"}) // 🔥 cegah loop equals
////public class Address {
////
////    @Id
////    private String id;
////
////    private String street;
////    private String city;
////    private String province;
////    private String country;
////
////    @Column(name = "postal_code")
////    private String postalCode;
////
////    @ManyToOne
////    @JoinColumn(name = "contact_id")
////    @JsonIgnore // 🔥 cegah loop JSON
////    private Contact contact;
////}
//
//package programmer.belajar.user;
//
//import jakarta.persistence.*;
//import lombok.*;
//import programmer.belajar.entity.Contact;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "addresses")
//public class Address {
//
//    @Id
//    private String id;
//
//    private String street;
//    private String city;
//    private String province;
//    private String country;
//
//    @Column(name = "postal_code")
//    private String postalCode;
//
//    @ManyToOne
//    @JoinColumn(name = "contact_id")
//    @com.fasterxml.jackson.annotation.JsonIgnore // 🔥 WAJIB
//    private Contact contact;
//}

package programmer.belajar.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import programmer.belajar.entity.Contact;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    private String id;

    private String street;
    private String city;
    private String province;
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;
}