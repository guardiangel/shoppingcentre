package com.felix.shoppingcentre.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * this is for using Jpa
 */
@Data
@Entity
@Table(name = "t_user")
public class UserJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name="phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "avatar")
    private String avatar;
    @Column(name="is_delete")
    private Integer delete;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column(name = "modified_time")
    private Date modifiedTime;
}
