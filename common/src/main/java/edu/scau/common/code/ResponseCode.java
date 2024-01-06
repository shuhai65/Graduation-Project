package edu.scau.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "Success");
    private final Integer code;
    private final String msg;
}
