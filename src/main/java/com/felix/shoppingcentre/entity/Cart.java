package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class Cart implements Serializable {
    /**
     * primary key
     */
    private Integer cid;

    /**
     * user id
     */
    private Integer uid;

    /**
     * product id
     */
    private Integer pid;

    /**
     * price
     */
    private BigInteger price;

    /**
     * purchase number
     */
    private Integer num;
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
