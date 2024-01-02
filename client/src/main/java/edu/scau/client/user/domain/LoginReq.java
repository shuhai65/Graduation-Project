package edu.scau.client.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@ApiModel(description = "用户登录参数")
public class LoginReq {
    @ApiModelProperty(value = "用户名")
    @Length(min = 5, max = 20, message = "用户名长度为5-20")
    private String username;
    @ApiModelProperty(value = "密码")
    @Length(min = 5, max = 20, message = "密码长度为5-20")
    private String password;
}
