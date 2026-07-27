package com.example.website.Controllers;

import com.example.website.DTOS.UserRequest;
import com.example.website.DTOS.UserResponse;
import com.example.website.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            UserResponse response = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(RuntimeException re){
            // TODO: temp error handling via try/catch, using ResponseEntity<?> to cover happy/sad path types
            // refactor to @RestControllerAdvice tomorrow
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsers(){
        List<UserResponse> responses = userService.listUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest request){
        try {
            UserResponse response = userService.updateUser(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(RuntimeException re) {
            // TODO: temp error handling via try/catch, using ResponseEntity<?> to cover happy/sad path types
            // refactor to @RestControllerAdvice tomorrow
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
        }

    }
}
