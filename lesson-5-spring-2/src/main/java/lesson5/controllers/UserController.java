package lesson5.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lesson5.exceptions.ResourceNotFoundException;
import lesson5.model.User;
import lesson5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "lesson4", description = "some manipulations with users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @ApiOperation("get all users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            return ResponseEntity.ok(userRepository.findAll());
        } catch (ResourceNotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    @ApiOperation("create a user")
    public ResponseEntity<User> addUser(User user) {
        try {
            return ResponseEntity.ok(userRepository.save(user));
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
