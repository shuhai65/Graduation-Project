package edu.scau.client.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ApiModel(description = "用户注册参数")
public class RegisterReq {
    @ApiModelProperty(value = "用户名")
    @Length(min = 5, max = 20, message = "用户名长度为5-20")
    private String username;
    @ApiModelProperty(value = "密码")
    @Length(min = 5, max = 20, message = "密码长度为5-20")
    private String password;
    @ApiModelProperty(value = "用户类型")
    @Max(value = 2, message = "用户类型只能为1或2")
    @Min(value = 1, message = "用户类型只能为1或2")
    private Integer role;
}
