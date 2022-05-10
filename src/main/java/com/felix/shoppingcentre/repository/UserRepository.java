package com.felix.shoppingcentre.repository;

import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.entity.UserJpaEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserJpaEntity, Integer> {
    UserJpaEntity findByUsername(String username);
}
