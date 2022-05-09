package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.User;

public interface UserMapper {
    Integer insert(User user);

    User findByUsername(String username);
}
