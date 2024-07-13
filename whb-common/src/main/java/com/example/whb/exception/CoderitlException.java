package com.example.whb.exception;

import org.springframework.http.HttpStatus;

public class CoderitlException extends RuntimeException {
    private Integer code;
    private String msg;
    private Object data;

    public CoderitlException() {
        super();
    }

    public CoderitlException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CoderitlException(String msg) {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.msg = msg;
    }

    public CoderitlException(Integer code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
