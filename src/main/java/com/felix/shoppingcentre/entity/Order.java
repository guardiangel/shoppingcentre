package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class Order implements Serializable {
    /**
     * order id
     */
    private Integer oid;
    /**
     * user id
     */
    private Integer uid;
    /**
     * receiver
     */
    private String recvName;
    /**
     * receiver's phone
     */
    private String recvPhone;
    /**
     * receiver's province
     */
    private String recvProvince;
    /**
     * receiver's city
     */
    private String recvCity;
    /**
     * reciever's area
     */
    private String recvArea;
    /**
     * receiver's address
     */
    private String recvAddress;
    /**
     * total price
     */
    private BigInteger totalPrice;

    /**
     * Status: 0-unpaid, 1-paid, 2-cancelled, 3-closed, 4-completed
     */
    private Integer status;
    /**
     * order time
     */
    private Date orderTime;
    /**
     * payment time
     */
    private Date payTime;

    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

}
