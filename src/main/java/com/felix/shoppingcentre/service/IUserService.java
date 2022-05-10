package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.User;

/**
 * user service interface
 */
public interface IUserService {
    void register(User user);

    User login(String username, String password);

    void updatePassword(Integer uid, String username,
                           String originalPassword, String newPassword);
}
