//////package programmer.belajar.controller;
//////
//////import org.springframework.web.bind.annotation.GetMapping;
//////import org.springframework.web.bind.annotation.RequestMapping;
//////import org.springframework.web.bind.annotation.RestController;
//////
//////@RestController
//////@RequestMapping("/api/v1/admin")
//////public class AdminController {
//////
//////    @GetMapping("/test")
//////    public String adminTest() {
//////        return "ADMIN ACCESS";
//////    }
//////}
////
//////package programmer.belajar.controller;
//////
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.security.access.prepost.PreAuthorize;
//////import org.springframework.web.bind.annotation.*;
//////import programmer.belajar.model.UpdateUserRequest;
//////import programmer.belajar.model.UserResponse;
//////import programmer.belajar.service.UserService;
//////
//////@RestController
//////@RequiredArgsConstructor
//////@RequestMapping("/api/v1/admin")
//////public class AdminController {
//////
//////    private final UserService userService;
//////
//////    // DELETE USER
//////    @DeleteMapping("/{email}")
//////    @PreAuthorize("hasRole('ADMIN')")
//////    public String deleteUser(@PathVariable String email) {
//////        userService.deleteByAdmin(email);
//////        return "User deleted";
//////    }
//////
//////    // UPDATE USER
//////    @PatchMapping("/{email}")
//////    @PreAuthorize("hasRole('ADMIN')")
//////    public UserResponse updateUser(
//////            @PathVariable String email,
//////            @RequestBody UpdateUserRequest request
//////    ) {
//////        return userService.updateByAdmin(email, request);
//////    }
//////}
////
////
////
///////  ADMIN BISA DELETE SENDIRI
////package programmer.belajar.controller;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.access.prepost.PreAuthorize;
////import org.springframework.web.bind.annotation.*;
////import programmer.belajar.model.UpdateUserRequest;
////import programmer.belajar.model.UserResponse;
////import programmer.belajar.service.UserService;
////
////@RestController
////@RequiredArgsConstructor
////@RequestMapping("/api/v1/admin")
////public class AdminController {
////
////    private final UserService userService;
////
////    @DeleteMapping("/{email}")
////    @PreAuthorize("hasRole('ADMIN')")
////    public String deleteUser(@PathVariable String email) {
////        userService.deleteByAdmin(email);
////        return "User deleted";
////    }
////
////    @PatchMapping("/{email}")
////    @PreAuthorize("hasRole('ADMIN')")
////    public UserResponse updateUser(
////            @PathVariable String email,
////            @RequestBody UpdateUserRequest request
////    ) {
////        return userService.updateByAdmin(email, request);
////    }
////
////    @GetMapping("/test")
////    public String test() {
////        return "ADMIN ACCESS";
////    }
////}
//
//
//package programmer.belajar.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import programmer.belajar.model.UpdateRoleRequest;
//import programmer.belajar.model.UpdateUserRequest;
//import programmer.belajar.model.UserResponse;
//import programmer.belajar.service.UserService;
//import programmer.belajar.user.User;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/admin")
//public class AdminController {
//
//    private final UserService userService;
//
//    // =========================
//    // 🔥 DELETE USER (ADMIN)
//    // =========================
//    @DeleteMapping("/{email}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String deleteUser(
//            @PathVariable String email,
//            @AuthenticationPrincipal User admin
//    ) {
//        userService.deleteByAdmin(email, admin.getEmail());
//        return "User deleted";
//    }
//
//
//    // UPDATE USER (ADMIN)
//
//    @PatchMapping("/{email}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public UserResponse updateUser(
//            @PathVariable String email,
//            @RequestBody UpdateUserRequest request,
//            @AuthenticationPrincipal User admin
//    ) {
//        return userService.updateByAdmin(email, admin.getEmail(), request);
//    }
//    // TEST ENDPOINT
//    @GetMapping("/test")
//    public String test() {
//        return "ADMIN ACCESS";
//    }
//
//    @PatchMapping("/role/{email}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String updateRole(
//            @PathVariable String email,
//            @RequestBody UpdateRoleRequest request,
//            @AuthenticationPrincipal User admin
//    ) {
//        userService.updateRoleByAdmin(email, admin.getEmail(), request);
//        return "Role updated";
//    }
//}

package programmer.belajar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import programmer.belajar.model.UpdateRoleRequest;
import programmer.belajar.model.UpdateUserRequest;
import programmer.belajar.model.UserResponse;
import programmer.belajar.service.UserService;
import programmer.belajar.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    // ✅ GET ALL USERS (CRITICAL)
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // DELETE USER
    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(
            @PathVariable String email,
            @AuthenticationPrincipal User admin
    ) {
        userService.deleteByAdmin(email, admin.getEmail());
        return "User deleted";
    }

    // UPDATE USER
    @PatchMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(
            @PathVariable String email,
            @RequestBody UpdateUserRequest request,
            @AuthenticationPrincipal User admin
    ) {
        return userService.updateByAdmin(email, admin.getEmail(), request);
    }

    // UPDATE ROLE
    @PatchMapping("/role/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRole(
            @PathVariable String email,
            @RequestBody UpdateRoleRequest request,
            @AuthenticationPrincipal User admin
    ) {
        userService.updateRoleByAdmin(email, admin.getEmail(), request);
        return "Role updated";
    }
}