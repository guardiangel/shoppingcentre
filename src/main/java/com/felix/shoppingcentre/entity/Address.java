package com.felix.shoppingcentre.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Address implements Serializable {
    /**
     * primary key
     */
    private Integer aid;
    /**
     * user id
     */
    private Integer uid;
    /**
     * address name
     */
    @NotBlank(message = "please input receiver")
    private String name;
    /**
     * province name
     */
    private String provinceName;
    /**
     * province code
     */
    private String provinceCode;
    /**
     * city name
     */
    private String cityName;
    /**
     * city code
     */
    private String cityCode;
    /**
     * district name
     */
    private String areaName;
    /**
     * district code
     */
    private String areaCode;
    /**
     * postal code
     */
    @NotBlank(message = "please input zipcode")
    @Length(message = "zip can not exceed {max} digits", max = 6)
    private String zip;
    /**
     * address
     */
    private String address;
    @NotBlank
    @Length(message = "phone number must be {max} digits ", max = 11)
    private String phone;
    /**
     * telephone number
     */
    private String tel;
    /**
     * tag
     */
    private String tag;
    /**
     * 1 for primary address
     */
    private Integer primaryAddress;

    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
