//package programmer.belajar.demo;
//
//import jakarta.persistence.Entity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/demo-controller")
//public class DemoController {
//
//    @GetMapping
//    public ResponseEntity<String> sayHello() {
//        return ResponseEntity.ok("Hello from secured endpoint");
//
//    }
//}

package programmer.belajar.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}