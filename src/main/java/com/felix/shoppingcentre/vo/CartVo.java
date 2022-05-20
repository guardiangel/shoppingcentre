package com.felix.shoppingcentre.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * display products in cart
 */
@Data
public class CartVo implements Serializable {

    /**
     * cart id
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
     * number
     */
    private Integer num;
    /**
     * title
     */
    private String title;
    /**
     * real price
     */
    private BigInteger realPrice;
    /**
     * image
     */
    private String image;

}
