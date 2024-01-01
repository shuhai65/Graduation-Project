package edu.scau.common.hander;


import edu.scau.common.ResultData;
import edu.scau.common.code.SystemErrorCode;
import edu.scau.common.code.UserErrorCode;
import edu.scau.common.exception.AccessDeniedException;
import edu.scau.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * json 格式错误 缺少请求体
     */
    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResultData<?> jsonFormatExceptionHandler(Exception e) {
        return ResultData.error(UserErrorCode.PARAM_ERROR, "参数JSON格式错误");
    }

    /**
     * json 格式错误 缺少请求体
     */
    @ResponseBody
    @ExceptionHandler({TypeMismatchException.class, BindException.class})
    public ResultData<?> paramExceptionHandler(Exception e) {
        if (e instanceof BindException) {
            if (e instanceof MethodArgumentNotValidException) {
                List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
                List<String> msgList = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                return ResultData.error(UserErrorCode.PARAM_ERROR, String.join(",", msgList));
            }

            List<FieldError> fieldErrors = ((BindException) e).getFieldErrors();
            List<String> error = fieldErrors.stream().map(field -> field.getField() + ":" + field.getRejectedValue()).collect(Collectors.toList());
            String errorMsg = UserErrorCode.PARAM_ERROR.getMsg() + ":" + error;
            return ResultData.error(UserErrorCode.PARAM_ERROR, errorMsg);
        }
        return ResultData.error(UserErrorCode.PARAM_ERROR);
    }

    /**
     * 权限异常
     */
    @ResponseBody
    @ExceptionHandler({AccessDeniedException.class})
    public ResultData<?> permissionExceptionHandler(AccessDeniedException e) {
        return ResultData.error(UserErrorCode.NO_PERMISSION);
    }

    /**
     * 业务异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResultData<?> businessExceptionHandler(BusinessException e) {
        return ResultData.error(SystemErrorCode.SYSTEM_ERROR, e.getMessage());
    }

    /**
     * 其他全部异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResultData<?> errorHandler(Throwable e) {
        log.error("捕获全局异常,URL:{}", getCurrentRequestUrl(), e);
        return ResultData.error(SystemErrorCode.SYSTEM_ERROR, "系统异常");
    }

    /**
     * 获取当前请求url
     */
    private String getCurrentRequestUrl() {
        RequestAttributes request = RequestContextHolder.getRequestAttributes();
        if (null == request) {
            return null;
        }
        ServletRequestAttributes servletRequest = (ServletRequestAttributes) request;
        return servletRequest.getRequest().getRequestURI();
    }


}
