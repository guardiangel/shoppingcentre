package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.User;

/**
 * user dao
 */
public interface UserMapper {
    /**
     * add user
     * @param user
     *      user object
     * @return
     *      1 for success
     */
    Integer insert(User user);

    /**
     * query user based on username
     * @param username
     *      username
     * @return
     *      user object
     */
    User findByUsername(String username);

    /**
     * modify user's passwrod
     * @param user
     * @return
     *  1 for success
     */
    Integer updatePassword(User user);

    /**
     * query user based on id
     * @param uid
     *   userid
     * @return
     *  user object
     */
    User findByUid(Integer uid);

    /**
     * update user info
     * @param user
     * @return
     */
    Integer updateUserInfo(User user);

    /**
     * upload head photo
     * @param user
     * @return
     */
    Integer updateUserAvatar(User user);
}
