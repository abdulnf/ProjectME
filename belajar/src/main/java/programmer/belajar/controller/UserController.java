//////package programmer.belajar.controller;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.http.MediaType;
//////import org.springframework.web.bind.annotation.*;
////////import programmer.belajar.entity.User;
//////import programmer.belajar.model.RegisterUserRequest;
//////import programmer.belajar.model.UpdateUserRequest;
//////import programmer.belajar.model.UserResponse;
//////import programmer.belajar.model.WebResponse;
//////import programmer.belajar.service.UserService;
//////import programmer.belajar.user.User;
//////
//////@RestController
//////public class UserController {
//////
//////    @Autowired
//////    private UserService userService;
//////
//////    @PostMapping(
//////            path = "/api/users",
//////            consumes = MediaType.APPLICATION_JSON_VALUE,
//////            produces = MediaType.APPLICATION_JSON_VALUE
//////    )
//////    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
//////        userService.register(request);
//////        return WebResponse.<String>builder().data("OK").build();
//////
//////    }
//////
//////    @GetMapping(
//////            path = "/api/users/current",
//////            produces = MediaType.APPLICATION_JSON_VALUE
//////    )
//////    public WebResponse<UserResponse> get(User user) {
//////    UserResponse userResponse = userService.get(user);
//////    return WebResponse.<UserResponse>builder().data(userResponse).build();
//////    }
//////
//////    @PatchMapping(
//////            path = "api/users/current",
//////            consumes = MediaType.APPLICATION_JSON_VALUE,
//////            produces = MediaType.APPLICATION_JSON_VALUE
//////    )
//////    public WebResponse<UserResponse> update (User user, @RequestBody UpdateUserRequest request) {
//////        UserResponse userResponse = userService.update(user, request);
//////        return WebResponse.<UserResponse>builder().data(userResponse).build();
//////    }
//////
//////}
////
////
////package programmer.belajar.controller;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.MediaType;
////import org.springframework.security.core.annotation.AuthenticationPrincipal;
////import org.springframework.web.bind.annotation.*;
////import programmer.belajar.model.*;
////import programmer.belajar.service.UserService;
////import programmer.belajar.user.User;
////
////
////@RestController
////@RequiredArgsConstructor
////@RequestMapping("/api/v1/users")
////public class UserController {
////
////    private final UserService userService;
////
////    @GetMapping(
////            path = "/current",
////            produces = MediaType.APPLICATION_JSON_VALUE
////    )
////    public WebResponse<UserResponse> get(@AuthenticationPrincipal User user) {
////        UserResponse response = userService.get(user);
////        return WebResponse.<UserResponse>builder().data(response).build();
////    }
////
////    @PatchMapping(
////            path = "/current",
////            consumes = MediaType.APPLICATION_JSON_VALUE,
////            produces = MediaType.APPLICATION_JSON_VALUE
////    )
////    public WebResponse<UserResponse> update(
////            @AuthenticationPrincipal User user,
////            @RequestBody UpdateUserRequest request
////    ) {
////        UserResponse response = userService.update(user, request);
////        return WebResponse.<UserResponse>builder().data(response).build();
////    }
////}
//
//package programmer.belajar.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import programmer.belajar.model.*;
//import programmer.belajar.service.UserService;
//import programmer.belajar.user.User;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/users")
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping(
//            path = "/current",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<UserResponse> get(@AuthenticationPrincipal User user) {
//        UserResponse response = userService.get(user);
//        return WebResponse.<UserResponse>builder().data(response).build();
//    }
//
//    @PatchMapping(
//            path = "/current",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<UserResponse> update(
//            @AuthenticationPrincipal User user,
//            @RequestBody UpdateUserRequest request
//    ) {
//        UserResponse response = userService.update(user, request);
//        return WebResponse.<UserResponse>builder().data(response).build();
//    }
//
//    // ✅ TEST USER ACCESS
//    @GetMapping("/test")
//    public String test(@AuthenticationPrincipal User user) {
//        return "USER ACCESS: " + user.getEmail();
//    }
//}

package programmer.belajar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import programmer.belajar.model.*;
import programmer.belajar.service.UserService;
import programmer.belajar.user.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // Get Current user by Jwt token
    @GetMapping("/current")
    public WebResponse<UserResponse> get(@AuthenticationPrincipal User user) {
        return WebResponse.<UserResponse>builder()
                .data(userService.get(user))
                .build();
    }

    @PatchMapping("/current")
    public WebResponse<UserResponse> update(
            @AuthenticationPrincipal User user,
            @RequestBody UpdateUserRequest request
    ) {
        return WebResponse.<UserResponse>builder()
                .data(userService.update(user, request))
                .build();
    }

    // TEST USER ACCESS
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal User user) {
        return "USER ACCESS: " + user.getEmail();
    }
}