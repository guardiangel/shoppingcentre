package com.felix.shoppingcentre.exception;

/**
 * response code
 */
public enum ExceptionResponseCode implements ResponseCodeInterface {

    SUCCESS(0, "success"),
    GOODS_NO_FOUND(40001, "goods not found"),

    USER_NOT_FOUND(50001, "user not found"),
    USER_REPEAT(50002, "user exists in the database"),
    PASSWORD_ERROR(50003, "password doesn't match the existing one"),
    UNKNOW_ERROR(99999, "unknown exception, please contact administrator"),
    ;

    int code;
    String msg;

    ExceptionResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
