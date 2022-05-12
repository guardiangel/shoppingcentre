package com.felix.shoppingcentre.exception;

/**
 * response code
 */
public enum ExceptionResponseCode implements ResponseCodeInterface {

    SUCCESS(0, "success"),
    GOODS_NO_FOUND(40001, "goods not found"),

    USER_DELETED(50000, "user has been deleted"),
    USER_NOT_FOUND(50001, "user not found"),
    USER_REPEAT(50002, "user exists in the database"),
    USER_UPDATE_ERROR(50003, "there is an error when update user info"),
    PASSWORD_ERROR(60001, "password doesn't match the existing one"),
    PASSWORD_EQUAL_ORIGINAL(60002, "password can't equal to original one"),
    PASSWORD_INPUT_WRONG(60003, "the original password you input don't match existing one"),
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
