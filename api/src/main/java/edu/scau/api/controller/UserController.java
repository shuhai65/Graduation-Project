package edu.scau.api.controller;

import edu.scau.client.image.domain.ImageVo;
import edu.scau.client.user.domain.req.UserInfoUpdateReq;
import edu.scau.client.user.domain.vo.UserInfoVo;
import edu.scau.client.user.service.UserService;
import edu.scau.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Api(value = "UserController", tags = "用户管理接口")
public class UserController {

    /**
     * 1.获取用户信息
     * 2.更新用户信息
     * 3.上传头像
     * 4.获取用户头像
     * 5.获取用户信息
     */

    @Autowired
    UserService userService;

    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息")
    public ResultData<UserInfoVo> getUserInfo() {
        UserInfoVo userInfoVo = userService.getUserInfo();
        return ResultData.success(userInfoVo);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息")
    public ResultData<Object> updateUserInfo(@RequestBody @Validated UserInfoUpdateReq userInfoUpdateReq) {
        userService.updateUserInfo(userInfoUpdateReq);
        return ResultData.success();
    }

    @ApiOperation(value = "上传头像", notes = "传入图片信息，返回图片id")
    @PostMapping("/uploadAvatar")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "file", value = "图片文件", required = true, paramType = "query", dataTypeClass = MultipartFile.class)
    )
    public ResultData<Long> uploadAvatar(@RequestParam("file") MultipartFile file) {
        Long id = userService.uploadAvatar(file);
        return ResultData.success(id);
    }

    @ApiOperation(value = "获取用户头像")
    @GetMapping("/getAvatar")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataTypeClass = Long.class)
    )
    public ResultData<ImageVo> getAvatar(@RequestParam("id") Long id) {
        String data = userService.getAvatar(id);
        ImageVo imageVo = new ImageVo();
        imageVo.setData(data);
        return ResultData.success(imageVo);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getUserInfoById")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataTypeClass = Long.class)
    )
    public ResultData<UserInfoVo> getUserInfoById(@RequestParam("id") Long id) {
        UserInfoVo userInfoVo = userService.getUserInfoById(id);
        return ResultData.success(userInfoVo);
    }

}
