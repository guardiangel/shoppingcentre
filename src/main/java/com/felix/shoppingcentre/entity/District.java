package com.felix.shoppingcentre.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class District implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * parent of code
     */
    private String parent;
    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
}
