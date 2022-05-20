package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user entity
 */
@Data
public class User implements Serializable {
    /**
     * primary key
     */
    private Integer uid;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * used for encrypting password
     */
    private String salt;
    /**
     * phone number
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * gender
     */
    private Integer gender;
    /**
     * head photo
     */
    private String avatar;
    /**
     * if user is deleted
     */
    private Integer delete;
    
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
