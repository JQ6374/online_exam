package com.zz.utils.result;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class ApiResult {

    private Integer code;
    private Object data;
    private String msg;

    public ApiResult() {
    }

    public ApiResult(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
