package edu.scau.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisEnum {
    LOGIN_USER("login_user:", "登录用户信息"),
    SPLIT(":", "分隔符"),
    PREFIX_ENTITY_LIKE("like:entity:", "点赞实体"),
    PREFIX_USER_LIKE("like:user:", "点赞用户"),
    PREFIX_FOLLOWEE("followee:", "关注对象"),
    PREFIX_FOLLOWER("follower:", "粉丝"),
    PREFIX_USER("user:", "用户"),
    PREFIX_USER_INFO("user_info:", "用户信息"),
    PREFIX_USER_AVATAR("user_avatar:", "用户头像"),
    PREFIX_POST("post:", "帖子"),
    PREFIX_SCORE("score:", "分数"),
    PREFIX_TASK("task:", "任务"),
    PREFIX_TODAY_TASK("today_task:", "今日任务"),
    PREFIX_NOTICE("notice:", "通知"),
    PREFIX_TASK_QUEUE("task_queue:", "任务队列"),
    EXPIRE_TIME("259200", "过期时间"),
    CAROUSEL("carousel", "轮播图"),
    EMAIL_CODE("email_code", "邮箱验证码"),
    EMAIL_CODE_EXPIRE_TIME("300", "邮箱验证码过期时间"),
    USER_INFO_EXPIRE_TIME("5600", "用户信息过期时间"),
    TOKEN_EXPIRE_TIME("2505600", "token过期时间");
    private final String value;

    private final String desc;

}
