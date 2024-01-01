package edu.scau.common;

import edu.scau.common.code.ErrorCode;
import edu.scau.common.code.ResponseCode;
import edu.scau.common.code.UserErrorCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ResultData<T> {

    // 结果状态码
    private Integer code;

    // 响应信息
    private String msg;

    // 响应数据
    private T data;

    //错误级别
    private String level;

    //是否成功
    private Boolean ok;

    public ResultData(Integer code, String level, boolean ok, String msg, T data) {
        this.code = code;
        this.level = level;
        this.ok = ok;
        this.msg = msg;
        this.data = data;
    }

    public ResultData(ErrorCode errorCode, boolean ok, String msg, T data) {
        this.code = errorCode.getCode();
        this.level = errorCode.getLevel();
        this.ok = ok;
        if (StringUtils.isNotBlank(msg)) {
            this.msg = msg;
        } else {
            this.msg = errorCode.getMsg();
        }
        this.data = data;
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(ResponseCode.SUCCESS.getCode(), null, true, ResponseCode.SUCCESS.getMsg(), data);
    }

    public static <T> ResultData<T> success() {
        return new ResultData<>(ResponseCode.SUCCESS.getCode(), null, true, ResponseCode.SUCCESS.getMsg(), null);
    }

    public static <T> ResultData<T> success(String msg) {
        return new ResultData<>(ResponseCode.SUCCESS.getCode(), null, true, msg, null);
    }

    public static <T> ResultData<T> userErrorParam() {
        return new ResultData<>(UserErrorCode.PARAM_ERROR, false, null, null);
    }


    public static <T> ResultData<T> userErrorParam(String msg) {
        return new ResultData<>(UserErrorCode.PARAM_ERROR, false, msg, null);
    }

    public static <T> ResultData<T> error(ErrorCode errorCode) {
        return new ResultData<>(errorCode, false, null, null);
    }

    public static <T> ResultData<T> error(ErrorCode errorCode, boolean ok) {
        return new ResultData<>(errorCode, ok, null, null);
    }

    public static ResultData error(ResultData responseDTO) {
        return new ResultData<>(responseDTO.getCode(), responseDTO.getLevel(), responseDTO.getOk(), responseDTO.getMsg(), responseDTO.getData());
    }

    public static <T> ResultData<T> error(ErrorCode errorCode, String msg) {
        return new ResultData<>(errorCode, false, msg, null);
    }

    public static <T> ResultData<T> errorData(ErrorCode errorCode, T data) {
        return new ResultData<>(errorCode, false, null, data);
    }


}

