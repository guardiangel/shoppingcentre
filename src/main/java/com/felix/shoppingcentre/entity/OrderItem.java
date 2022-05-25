package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class OrderItem implements Serializable {
    /**
     * primary key
     */
    private Integer id;
    /**
     * order id
     */
    private Integer oid;
    /**
     * product id
     */
    private Integer pid;
    /**
     * product title
     */
    private String title;
    /**
     * product image
     */
    private String image;

    /**
     * price
     */
    private BigInteger price;
    /**
     * purchase quantity
     */
    private Integer num;

    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
