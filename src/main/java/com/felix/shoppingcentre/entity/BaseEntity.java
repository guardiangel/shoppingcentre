package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * base entity
 */
@Data
public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
