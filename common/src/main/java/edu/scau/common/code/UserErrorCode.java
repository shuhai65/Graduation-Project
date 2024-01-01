package edu.scau.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户级别的错误码（用户引起的错误返回码，可以不用关注）
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    PARAM_ERROR(30001, "参数错误"),

    DATA_NOT_EXIST(30002, "找不到数据"),

    ALREADY_EXIST(30003, "数据已存在了"),

    REPEAT_SUBMIT(30004, "您操作的太快了，请稍等重试"),

    NO_PERMISSION(30005, "无权限"),

    DEVELOPING(30006, "系統正在开发中，敬请期待~"),

    LOGIN_STATE_INVALID(30007, "您还未登录或登录失效，请重新登录！"),

    LOGIN_FAIL(30008, "登录失败，请重新登录！"),

    USER_STATUS_ERROR(30008, "用户状态异常"),

    FORM_REPEAT_SUBMIT(30009, "请勿重复提交");

    private final int code;

    private final String msg;

    private final String level;

    UserErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_USER;
    }
}
