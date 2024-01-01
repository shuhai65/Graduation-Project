package edu.scau.common.constant.user;

import edu.scau.common.constant.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserTypeEnum implements BaseEnum {
    /**
     * 未知性别
     */
    SEX_UNKNOWN(0, "unknown"),
    /**
     * 男
     */
    SEX_MAN(1, "MAN"),
    /**
     * 女
     */
    SEX_WOMAN(2, "WOMAN");

    private final Integer value;

    private final String desc;
}
