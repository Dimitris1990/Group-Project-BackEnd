package com.financialservices.controllers;

import com.financialservices.exception.ResourceNotFoundException;
import com.financialservices.repository.UserRepository;
import com.financialservices.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Component
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public List<User> getUser() {
        return userRepo.findAll();
    }


    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        user.setActv(userDetails.getActv());
        User updatedUser = userRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    


//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteUserById(@PathVariable(value = "id") Long userId) {
//        userRepo.deleteById(userId);
//        return ResponseEntity.ok("User deleted successfully, ID:" + userId);
//    }
//    


}
