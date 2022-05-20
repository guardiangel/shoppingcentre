package com.felix.shoppingcentre.exception;

/**
 * response code
 */
public enum ExceptionResponseCode implements ResponseCodeInterface {

    DATA_VALIDATION(99998, ""),
    SUCCESS(0, "success"),
    UNKNOW_ERROR(99999, "unknown exception, please contact administrator"),

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

    ADDRESS_OVER_LIMIT(80001, "address count has exceeded the limit"),
    ADDRESS_NOT_FOUND(80002, " address not found"),
    ADDRESS_ACCESS_DENIED(80003, "not legal request to access the modification"),
    ADDRESS_SETTONODEFAULT_ERROR(80004, "error when set all addresses to non-default"),
    ADDRESS_SETUP_DEFAULT(80005, "error when set to defualt"),
    ADDRESS_DELETE_ERROR(80006, "error when delete one address"),

    PRODUCT_NOT_FOUND(400001, "product not found"),

    CART_INSERT_ERROR(30001, "exception when add cart"),
    CART_UPATE_ERROR(30002, "exception when update cart");

    final int code;
    final String msg;

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
