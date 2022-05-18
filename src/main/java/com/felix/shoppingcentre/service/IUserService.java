package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.User;

/**
 * user service interface
 */
public interface IUserService {
    /**
     * user registration
     *
     * @param user user entity object
     */
    void register(User user);

    /**
     * user login
     *
     * @param username username
     * @param password password
     * @return user entity object
     */
    User login(String username, String password);

    /**
     * update password
     *
     * @param uid              user id
     * @param username         username
     * @param originalPassword original password
     * @param newPassword      new password
     */
    void updatePassword(Integer uid, String username,
                        String originalPassword, String newPassword);

    User findByUserId(Integer uid);

    /**
     * update user info
     *
     * @param user user object
     */
    void updateUserInfo(User user);

    /**
     * update user's avatar
     *
     * @param user user object
     */
    void updateUserAvatar(User user);


}
