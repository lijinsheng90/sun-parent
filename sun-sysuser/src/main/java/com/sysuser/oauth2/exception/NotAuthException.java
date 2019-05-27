package com.sysuser.oauth2.exception;

/**
 * @author lijinsheng
 * @date Time 2018/8/6 15:56
 *  没有认证或是token过期
 **/
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        this("没有认证！");
    }

    public NotAuthException(String message) {
        super(message);
    }
}
