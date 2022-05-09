package com.felix.shoppingcentre.exception;

/**
 * exception definition
 */
public class ServiceException extends RuntimeException {

    private final int messageCode;
    private final String messageDetail;

    public ServiceException(int messageCode, String messageDetail) {
        this.messageCode = messageCode;
        this.messageDetail = messageDetail;
    }

    public ServiceException(ResponseCodeInterface codeInterface) {
        this(codeInterface.getCode(), codeInterface.getMsg());
    }

    public ServiceException(String messageDetail) {
        this(-9999, messageDetail);
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getMessageDetail() {
        return messageDetail;
    }
}
