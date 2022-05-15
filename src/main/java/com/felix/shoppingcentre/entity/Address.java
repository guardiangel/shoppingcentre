package com.felix.shoppingcentre.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class Address implements Serializable {
    private Integer aid;
    private Integer uid;
    @NotBlank(message = "please input receiver")
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    @NotBlank(message = "please input zipcode")
    @Length(message = "zip can not exceed {max} digits", max = 6)
    private String zip;
    private String address;
    @NotBlank
    @Length(message = "phone number must be {max} digits ", max = 11)
    private String phone;
    private String tel;
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
