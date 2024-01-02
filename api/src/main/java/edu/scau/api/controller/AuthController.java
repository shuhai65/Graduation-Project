package edu.scau.api.controller;

import cn.hutool.http.HttpRequest;
import edu.scau.client.user.domain.EmailResetPasswordReq;
import edu.scau.client.user.domain.LoginReq;
import edu.scau.client.user.domain.RegisterReq;
import edu.scau.client.user.domain.ResetPasswordReq;
import edu.scau.client.user.service.UserService;
import edu.scau.common.ResultData;
import edu.scau.common.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Api(value = "AuthController", tags = "用户认证接口")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResultData<String> login(@RequestBody @Validated LoginReq loginReq){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(loginReq.getUsername());
        userService.login(loginReq,token);
        String Authorization = "Bearer:" + token;
        return ResultData.success(Authorization);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResultData register(@RequestBody @Validated RegisterReq registerReq){
        userService.register(registerReq);
        return ResultData.success();
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户登出")
    public ResultData logout(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String Authorization = "Bearer:" + authorization.substring(7);
        System.out.println(Authorization);
        userService.logout(Authorization);
        return ResultData.success();
    }

    @PostMapping("/email/resetPassword")
    @ApiOperation(value = "发送重置密码邮件")
    public ResultData sendEmail(@RequestBody @Validated EmailResetPasswordReq emailResetPasswordReq){
        userService.sendEmail(emailResetPasswordReq);
        return ResultData.success();
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码")
    public ResultData resetPassword(@RequestBody @Validated ResetPasswordReq resetPasswordReq){
        userService.resetPassword(resetPasswordReq);
        return ResultData.success();
    }
}
