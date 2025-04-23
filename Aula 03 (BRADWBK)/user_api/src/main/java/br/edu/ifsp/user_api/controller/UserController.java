package br.edu.ifsp.user_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ifsp.user_api.model.User;
import br.edu.ifsp.user_api.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUserList();
        return ResponseEntity.ok().body(users);
    }    

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getOneUser(
        @PathVariable int id
    ) {
        User user = userService.getUserById(id);
        if(user != null)
            return ResponseEntity.ok().body(user); // Status 220
        return ResponseEntity.notFound().build(); // Status 404
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable int id,
        @RequestBody User user
    ) {
        User updatedUser = userService.updateUser(id, user);
        if(updatedUser != null)
            return ResponseEntity.ok().body(updatedUser); // Status 200
        return ResponseEntity.notFound().build(); // Status 404
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable int id
    ) {
        boolean deleted = userService.deleteUser(id);
        if(deleted)
            return ResponseEntity.noContent().build(); // Status 204
        return ResponseEntity.notFound().build(); // Status 404
    }
}
