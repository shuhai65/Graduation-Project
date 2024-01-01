package edu.scau.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntityTypeEnum implements BaseEnum {

    NOT_DELETED(0, "not deleted"),
    DELETED(1, "deleted"),
    POST(1, "post"),
    COMMENT(2, "comment"),
    USER(3, "user");

    private final Integer value;

    private final String desc;
}
