package com.zz.aop;

import com.zz.exception.BusinessException;
import com.zz.exception.SystemException;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ProjectExceptionAdvice {

    // @ExceptionHandler用于设置当前处理器类对应的异常类型
    @ExceptionHandler(SystemException.class)
    public ApiResult doSystemException(SystemException e) {
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员,ex对象发送给开发人员
        return new ApiResult(e.getCode(), null, e.getMessage());
    }


    @ExceptionHandler(BusinessException.class)
    public ApiResult doBusinessException(BusinessException e) {
        return new ApiResult(e.getCode(), null, e.getMessage());
    }


    // 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
    @ExceptionHandler(Exception.class)
    public ApiResult doOtherException(Exception e) {
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员,ex对象发送给开发人员
        return new ApiResult(Code.SYSTEM_UNKNOWN_ERROR, null, Code.ERROR_MSG);
    }

}
