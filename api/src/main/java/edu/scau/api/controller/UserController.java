package edu.scau.api.controller;

import edu.scau.client.user.domain.req.UserInfoUpdateReq;
import edu.scau.client.user.domain.vo.UserInfoVo;
import edu.scau.client.user.service.UserService;
import edu.scau.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Api(value = "UserController", tags = "用户管理接口")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息")
    public ResultData<UserInfoVo> getUserInfo(){
        UserInfoVo userInfoVo = userService.getUserInfo();
        return ResultData.success(userInfoVo);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息")
    public ResultData<Object> updateUserInfo(@RequestBody @Validated UserInfoUpdateReq userInfoUpdateReq){
        userService.updateUserInfo(userInfoUpdateReq);
        return ResultData.success();
    }
}
