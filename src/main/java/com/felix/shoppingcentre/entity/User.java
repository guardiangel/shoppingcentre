package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user entity
 */
@Data
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer delete;
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
