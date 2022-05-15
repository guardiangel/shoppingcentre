package com.felix.shoppingcentre.exception;

/**
 * response code
 */
public enum ExceptionResponseCode implements ResponseCodeInterface {

    DATA_VALIDATION(99998, ""),
    SUCCESS(0, "success"),
    UNKNOW_ERROR(99999, "unknown exception, please contact administrator"),
    GOODS_NO_FOUND(40001, "goods not found"),

    USER_DELETED(50000, "user has been deleted"),
    USER_NOT_FOUND(50001, "user not found"),
    USER_REPEAT(50002, "user exists in the database"),
    USER_UPDATE_ERROR(50003, "there is an error when update user info"),
    PASSWORD_ERROR(60001, "password doesn't match the existing one"),
    PASSWORD_EQUAL_ORIGINAL(60002, "password can't equal to original one"),
    PASSWORD_INPUT_WRONG(60003, "the original password you input don't match existing one"),


    FILE_NULL(70000, "file is null, please choose a file"),
    FILE_OVER_MAX_SIZE(70001, "file size is over the limitation"),
    FILE_TYPE_ERROR(70002, "file type can't be accepted"),

    FILE_STATE_ABNORMAL(700003, "file state error, maybe it's moved or deleted"),
    FILE_UPLOAD_ERROR(70004, "there is an error when uploading avata, please try later"),
    ADDRESS_OVER_LIMIT(80001, "address count has exceeded the limit");


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
