package edu.scau.common.utils;


import edu.scau.common.domain.UserRedisBO;

public class AuthUserContext {
    private static final ThreadLocal<UserRedisBO> USER_INFO_IN_TOKEN_HOLDER = new ThreadLocal<>();

    public static UserRedisBO get() {
        return USER_INFO_IN_TOKEN_HOLDER.get();
    }

    public static void set(UserRedisBO userRedisBO) {
        USER_INFO_IN_TOKEN_HOLDER.set(userRedisBO);
    }

    public static void clean() {
        if (USER_INFO_IN_TOKEN_HOLDER.get() != null) {
            USER_INFO_IN_TOKEN_HOLDER.remove();
        }
    }
}
