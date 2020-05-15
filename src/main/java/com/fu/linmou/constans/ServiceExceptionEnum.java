package com.fu.linmou.constans;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/13 16:46
 * @Version: 1.0
 */
public enum ServiceExceptionEnum {
    ILLEGAL_ARGUMENT(100,"错误1"),
    REPETITIVE_OPERATION(200, "错误2"),
    REPETITIVE_OPERATION_2(300, "错误3"),

    MISSING_REQUEST_PARAM_ERROR(500,"错误"),

    SYS_ERROR(500,"我错了"),

    INVALID_REQUEST_PARAM_ERROR(500, "请求参数不合法");



    private int code;
    private String msg;

    ServiceExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
