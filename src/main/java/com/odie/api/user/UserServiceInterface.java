package com.odie.api.user;

public interface UserServiceInterface {

    public Long createUser(User user);

    public User updateUser(Long id, User user);
}
