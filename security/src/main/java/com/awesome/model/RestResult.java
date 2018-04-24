package com.awesome.model;

public class RestResult {
    public static final int SUCCESS_CODE = 0;
    public static final int FAILED_CODE = -1;

    private int code;
    private String desc;
    private Object data;

    public RestResult(int code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Object getData() {
        return data;
    }
}
