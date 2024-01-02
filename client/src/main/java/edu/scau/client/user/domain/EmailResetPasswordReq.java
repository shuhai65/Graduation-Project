package edu.scau.client.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@ApiModel(description = "发送重置密码邮件参数")
public class EmailResetPasswordReq {
    @ApiModelProperty(value = "用户名")
    @Length(min = 5, max = 20, message = "用户名长度为5-20")
    private String username;
    @ApiModelProperty(value = "邮箱")
    @Length(min = 1, max = 350, message = "邮箱长度为1-350")
    private String email;
}
