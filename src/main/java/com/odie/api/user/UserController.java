package com.odie.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("No such User."));
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    Long createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
