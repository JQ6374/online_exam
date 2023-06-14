package com.zz.aop;

import com.zz.exception.BusinessException;
import com.zz.exception.SystemException;
import com.zz.exception.TokenException;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    // @ExceptionHandler用于设置当前处理器类对应的异常类型
    @ExceptionHandler(SystemException.class)
    public ApiResult<Object> doSystemException(SystemException e) {
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员,ex对象发送给开发人员
        return new ApiResult<>(e.getCode(), null, e.getMessage());
    }


    @ExceptionHandler(BusinessException.class)
    public ApiResult<Object> doBusinessException(BusinessException e) {
        return new ApiResult<>(e.getCode(), null, e.getMessage());
    }

    // 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
    @ExceptionHandler(NullPointerException.class)
    public ApiResult<Object> doNullPointerException(NullPointerException e) {
        // 在手动组卷和随机组卷时，要按照一定的格式要求传递参数，参数名称错误的话，会报空指针异常
        return new ApiResult<>(Code.SYSTEM_UNKNOWN_ERROR, null, "参数传递错误！");
    }

    @ExceptionHandler(TokenException.class)
    public ApiResult<Object> doTokenException(TokenException e) {
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员,ex对象发送给开发人员
        return new ApiResult<>(e.getCode(), null, e.getMessage());
    }

    // 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
    @ExceptionHandler(Exception.class)
    public ApiResult<Object> doOtherException(Exception e) {
        return new ApiResult<>(Code.SYSTEM_UNKNOWN_ERROR, null, Code.ERROR_MSG);
    }

}
