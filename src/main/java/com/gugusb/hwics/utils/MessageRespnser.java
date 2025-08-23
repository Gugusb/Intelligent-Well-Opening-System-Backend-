package com.gugusb.hwics.utils;

import org.springframework.http.HttpStatus;

public class MessageRespnser<T> {
    private Integer code;
    private String message;
    private T data;

    public MessageRespnser(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;

    }

    //接口请求成功
    public static <T> MessageRespnser<T> success(T data){
        return new MessageRespnser<T>(HttpStatus.OK.value(), "success", data);
    }


    //接口请求未成功
    public static <T> MessageRespnser<T> unsuccess(T data){
        return new MessageRespnser<T>(HttpStatus.BAD_REQUEST.value(), "bad", data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
