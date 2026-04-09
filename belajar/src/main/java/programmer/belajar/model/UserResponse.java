//package programmer.belajar.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//
//public class UserResponse {
//
//        private String username;
//
//        private String name;
//
//}


package programmer.belajar.model;

import lombok.*;
import programmer.belajar.user.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

        private String email;
        private String name;
        private Role role;
}