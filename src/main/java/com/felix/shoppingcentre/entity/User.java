package com.felix.shoppingcentre.entity;

import lombok.Data;

/**
 * user entity
 */
@Data
public class User extends BaseEntity {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}