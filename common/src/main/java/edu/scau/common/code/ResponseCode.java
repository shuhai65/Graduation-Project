package edu.scau.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "Success"),
    NOT_FOUND(404, "Not-found"),
    BAD_REQUEST(400, "Bad_request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_LOGIN(402, "Not-login"),
    FORBIDDEN(403, "Forbidden"),
    REQUEST_TIMEOUT(408, "Request-Time-out"),
    INTERNAL_SERVER_ERROR(500, "Internal-Server-Error");
    private final Integer code;
    private final String msg;
}
