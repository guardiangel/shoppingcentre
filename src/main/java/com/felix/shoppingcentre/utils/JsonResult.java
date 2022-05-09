package com.felix.shoppingcentre.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * Json data
 * @param <T>
 */
@Data
public class JsonResult<T> implements Serializable {

    private Integer state;
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }
}
