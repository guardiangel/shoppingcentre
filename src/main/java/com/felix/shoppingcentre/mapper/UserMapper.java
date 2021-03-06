package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.User;
import org.springframework.stereotype.Repository;

/**
 * user dao
 */
@Repository
public interface UserMapper {
    /**
     * add user
     *
     * @param user user object
     * @return 1 for success
     */
    Integer insert(User user);

    /**
     * query user based on username
     *
     * @param username username
     * @return user object
     */
    User findByUsername(String username);

    /**
     * modify user's passwrod
     *
     * @param user user
     * @return 1 for success
     */
    Integer updatePassword(User user);

    /**
     * query user based on id
     *
     * @param uid userid
     * @return user object
     */
    User findByUid(Integer uid);

    /**
     * update user info
     *
     * @param user user
     * @return affected rows
     */
    Integer updateUserInfo(User user);

    /**
     * upload head photo
     *
     * @param user user
     * @return affected rows
     */
    Integer updateUserAvatar(User user);
}
