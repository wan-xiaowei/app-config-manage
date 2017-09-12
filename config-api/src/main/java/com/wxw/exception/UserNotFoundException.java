package com.wxw.exception;

/**
 * Created by Administrator on 2017/7/3.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg){
        super(msg);
    }
}
