package edu.scau.common.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import edu.scau.common.ResultData;
import edu.scau.common.code.UserErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ParamException {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return ResultData.error(UserErrorCode.PARAM_ERROR, msg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData handleConstraintViolationException(ConstraintViolationException ex) {
        return ResultData.error(UserErrorCode.PARAM_ERROR, ex.getMessage());
    }

    @ExceptionHandler({InvalidFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData handleInvalidFormatException(InvalidFormatException ex) {
        return ResultData.error(UserErrorCode.PARAM_ERROR, ex.getMessage());
    }
}