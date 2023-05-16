package com.zz.utils.result;


public class TempResult {

    private boolean flag;
    private String msg;

    public TempResult() {
    }

    public TempResult(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

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
