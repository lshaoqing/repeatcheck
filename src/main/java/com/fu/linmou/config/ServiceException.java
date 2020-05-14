package com.fu.linmou.config;

/**
 * @author linMou
 * @Description: 自定义全局Exception
 * @Date: 2020/5/14 10:15
 * @Version: 1.0
 */
public class ServiceException extends RuntimeException {
    private String message;
    private int code = 500;

    public ServiceException(String msg) {
        super(msg);
        this.message = msg;
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
    }

    public ServiceException(String msg, int code) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public ServiceException(String msg, int code, Throwable e) {
        super(msg, e);
        this.message = msg;
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
