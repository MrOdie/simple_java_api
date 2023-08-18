package com.odie.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repository;

    @Override
    public Long createUser(User user) {
        User newUser = new User();

        newUser.setActive(user.getActive());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());

        return repository.save(newUser).getId();
    }

    @Override
    public User updateUser(Long id, User user) {
        if (repository.findById(user.getId()).isPresent()) {
            User existingUser = repository.findById(id).get();
            user.setActive(user.getActive());
            user.setEmail(user.getEmail());
            user.setPassword(user.getPassword());
            user.setUsername(user.getUsername());

            User updatedUser = repository.save(existingUser);

            return new User(updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getActive());
        }

        return null;
    }
}
