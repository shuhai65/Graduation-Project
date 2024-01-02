package edu.scau.client.user.domain.req;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ApiOperation(value = "更新用户信息参数")
public class UserInfoUpdateReq {
    private String nickname;
    private String phone;
    private String email;
    private String address;
    @Max(value = 2, message = "性别只能为0、1或2")
    @Min(value = 0, message = "性别只能为0、1或2")
    private Integer sex;
}
