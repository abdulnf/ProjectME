//package programmer.belajar.auth;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class RefreshTokenRequest {
//
//    private String refreshToken;
//
//}

package programmer.belajar.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}