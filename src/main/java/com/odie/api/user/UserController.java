package com.odie.api.user;

import com.odie.api.user.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.FOUND)
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    EntityModel<User> one(@PathVariable Long id) {

        User user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> newUser(@RequestBody User newUser) {

        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/user")
    ResponseEntity<?> updateUser(@RequestParam Long userId, @RequestBody User updateUser) {

        repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        User updatedUser = userService.updateUser(userId, updateUser);

        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/user")
    ResponseEntity<?> deleteUser(@RequestParam Long userId) {
        repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        repository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
