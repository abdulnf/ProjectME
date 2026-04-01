//package programmer.belajar.controller;
//
//import jakarta.validation.ConstraintViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.server.ResponseStatusException;
//import programmer.belajar.model.WebResponse;
//
//@RestControllerAdvice
//public class ErrorController {
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException exception){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(WebResponse.<String>builder().errors(exception.getMessage()).build());
//
//    }
//
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception){
//        return ResponseEntity.status(exception.getStatusCode())
//                .body(WebResponse.<String>builder().errors(exception.getReason()).build());
//
//    }
//
//}


package programmer.belajar.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import programmer.belajar.model.WebResponse;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().errors(e.getMessage()).build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode())
                .body(WebResponse.<String>builder().errors(e.getReason()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<String>> generalException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(WebResponse.<String>builder().errors(e.getMessage()).build());
    }
}