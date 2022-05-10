package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.User;

/**
 * user service interface
 */
public interface IUserService {
    void register(User user);

    User login(String username, String password);
}
