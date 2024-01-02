package edu.scau.common.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCodeUtil {

    Random randObj = new Random();
    // 生成6位随机验证码
    public String generateCode() {
        return Integer.toString(100000 + randObj.nextInt(900000));
    }
    // 生成4位随机验证码
    public String generateCode4() {
        return Integer.toString(1000 + randObj.nextInt(9000));
    }

}
