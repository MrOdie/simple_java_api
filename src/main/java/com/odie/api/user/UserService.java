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
        if (repository.findById(id).isPresent()) {
            User existingUser = repository.findById(id).get();

            existingUser.setActive(user.getActive());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setUsername(user.getUsername());

            System.out.println("existingUser: " + existingUser);
            User updatedUser = repository.save(existingUser);
            System.out.println("UpdatedUser: " + updatedUser);

            return updatedUser;
        }

        return null;
    }
}
