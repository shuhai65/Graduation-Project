package edu.scau.client.user.service;

import edu.scau.client.domain.AquariumUser;
import edu.scau.client.mail.service.MailService;
import edu.scau.client.mapper.AquariumRoleMapper;
import edu.scau.client.mapper.AquariumUserMapper;
import edu.scau.client.user.domain.req.*;
import edu.scau.client.user.domain.vo.UserInfoVo;
import edu.scau.common.constant.RedisEnum;
import edu.scau.common.exception.BusinessException;
import edu.scau.common.utils.RandomCodeUtil;
import edu.scau.common.utils.RedisUtil;
import edu.scau.common.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class UserService {
    @Autowired
    private AquariumUserMapper aquariumUserMapper;
    @Autowired
    private AquariumRoleMapper aquariumRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RandomCodeUtil randomCodeUtil;
    @Autowired
    private TemplateEngine templateEngine;

    public void login(LoginReq loginReq, String token) {
        AquariumUser aquariumUser = aquariumUserMapper.selectByUsername(loginReq.getUsername());
        //Bearer
        String Authorization = "Bearer:" + token;
        redisUtil.set(Authorization, aquariumUser);
    }

    @Transactional
    public void register(RegisterReq registerReq) {
        AquariumUser aquariumUser = new AquariumUser();
        aquariumUser.setUsername(registerReq.getUsername());
        aquariumUser.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        aquariumUser.setRoleId(1L);
        try {
            aquariumUserMapper.insert(aquariumUser);
        } catch (Exception e) {
            throw new BusinessException("用户名已存在");
        }
    }

    public void logout(String Authorization) {
        redisUtil.del(Authorization);
        if (redisUtil.hasKey(Authorization)) {
            throw new RuntimeException("登出失败");
        }
    }

    @Transactional
    public void resetPassword(ResetPasswordReq resetPasswordReq) {
        //验证验证码
        String code = (String) redisUtil.get(RedisEnum.EMAIL_CODE.getValue() + ":" + resetPasswordReq.getUsername());
        if (!code.equals(resetPasswordReq.getCode())) {
            throw new RuntimeException("验证码错误");
        }
        //修改密码
        AquariumUser aquariumUser = aquariumUserMapper.selectByUsername(resetPasswordReq.getUsername());
        aquariumUser.setPassword(passwordEncoder.encode(resetPasswordReq.getPassword()));
        aquariumUserMapper.updateByPrimaryKeySelective(aquariumUser);
        //删除验证码
        redisUtil.del(RedisEnum.EMAIL_CODE.getValue() + ":" + resetPasswordReq.getUsername());
    }

    public void sendEmail(EmailResetPasswordReq emailResetPasswordReq) {
        //验证邮箱是否存在
        AquariumUser aquariumUser = aquariumUserMapper.selectByUsername(emailResetPasswordReq.getUsername());
        if (aquariumUser == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!aquariumUser.getEmail().equals(emailResetPasswordReq.getEmail())) {
            throw new RuntimeException("邮箱不正确");
        }
        //生成验证码
        String generateCode = randomCodeUtil.generateCode();
        //Verification_code.html
        Context context = new Context();
        context.setVariable("code", generateCode);
        String emailContent = templateEngine.process("Verification_code", context);
        //发送邮件
        mailService.sendMail(emailResetPasswordReq.getEmail(), "验证码", emailContent);
        //保存验证码
        redisUtil.set(RedisEnum.EMAIL_CODE.getValue() + ":" + emailResetPasswordReq.getUsername(), generateCode, Long.parseLong(RedisEnum.EMAIL_CODE_EXPIRE_TIME.getValue()));
    }

    public UserInfoVo getUserInfo() {
        Long id = SecurityUtil.getUserInfo().getId();
        AquariumUser aquariumUser = aquariumUserMapper.selectByPrimaryKey(id);
        return new UserInfoVo(aquariumUser);
    }

    @Transactional
    public void updateUserInfo(UserInfoUpdateReq userInfoUpdateReq) {
        Long id = SecurityUtil.getUserInfo().getId();
        AquariumUser aquariumUser = new AquariumUser().setAddress(userInfoUpdateReq.getAddress())
                .setEmail(userInfoUpdateReq.getEmail())
                .setNickname(userInfoUpdateReq.getNickname())
                .setPhone(userInfoUpdateReq.getPhone())
                .setSex(userInfoUpdateReq.getSex())
                .setId(id);
        aquariumUserMapper.updateByPrimaryKeySelective(aquariumUser);
    }
}
