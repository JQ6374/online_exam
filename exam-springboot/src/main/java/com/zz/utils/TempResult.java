package com.zz.utils;

public class TempResult {
    private boolean flag;

    private String msg;

    @Override
    public String toString() {
        return "TempResult{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
