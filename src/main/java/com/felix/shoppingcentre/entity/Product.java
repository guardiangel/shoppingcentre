package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Product implements Serializable {

    private Integer id;
    /**
     * product category id
     */
    private Integer categoryId;
    /**
     * product type
     */
    private String itemType;
    /**
     * product title
     */
    private String title;
    /**
     * key feature for selling
     */
    private String sellPoint;
    /**
     * price
     */
    private Long price;
    /**
     * product number
     */
    private Integer num;
    /**
     * product image
     */
    private String image;
    /**
     * 1 on sell 2 remove 3 delete
     */
    private Integer status;
    /**
     * product priority
     */
    private Integer priority;

    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
