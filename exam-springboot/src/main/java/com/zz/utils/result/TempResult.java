package com.zz.utils.result;

import lombok.Data;

@Data
public class TempResult {

    private boolean flag;
    private String msg;

    public TempResult() {
    }

    public TempResult(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }
}
