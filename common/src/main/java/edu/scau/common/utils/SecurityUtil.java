package edu.scau.common.utils;

import edu.scau.common.domain.UserRedisBO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtil {
    public UserRedisBO getUserInfo() {
        return AuthUserContext.get();
    }
}
