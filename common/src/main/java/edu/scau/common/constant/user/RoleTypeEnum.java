package edu.scau.common.constant.user;

import edu.scau.common.constant.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleTypeEnum implements BaseEnum {
    USER(0, "user"),
    ADMIN(1, "admin"),
    SUPER_ADMIN(2, "super_admin");

    private final Integer value;

    private final String desc;
}
