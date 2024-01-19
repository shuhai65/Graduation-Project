package edu.scau.client.user.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ApiModel(description = "用户信息更新参数")
public class UserInfoUpdateReq {
    private String username;
}
