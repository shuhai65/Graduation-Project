package edu.scau.client.user.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "重置密码参数")
public class ResetPasswordReq {
    private String username;
    private String email;
    private String password;
    private String code;
}
