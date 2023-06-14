package com.zz.exception;

public class TokenException extends RuntimeException {
    private Integer code;

    public TokenException(Integer code) {
        this.code = code;
    }

    public TokenException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public TokenException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
