package com.zz.utils;

/**
 * 状态码封装类
 */
public class Code {
    public static final Integer SAVA_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;

    public static final Integer SAVA_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;

    public static final Integer SYSTEM_UNKNOWN_ERROR = 50001;
    public static final Integer SYSTEM_TIMEOUT_ERROR = 50002;

    public static final Integer PROJECT_VALIDATE_ERROR = 60001;
    public static final Integer PROJECT_BUSINESS_ERROR = 60002;
}
