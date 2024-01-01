package edu.scau.common.utils;


import edu.scau.common.config.SystemConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String ID = "jti";

    @Autowired
    SystemConfig systemConfig;

    // 可供外界调用的方法：
    // 1. 根据用户信息生成 token
    // 4. 从 token 中获取登录用户名
    // 6. 验证 token 是否有效
    // 9. 判断 token 是否可以被刷新
    // 10. 刷新 token

    /**
     * 1. ☑️ 根据用户信息生成 token
     * 用户信息从 security 框架 UserDetails 中获取
     *
     * @param userId : 用户id
     * @return : 返回重载方法generateToken(claims),根据荷载生成 JWT token
     */
    public String generateToken(String userId) {
        // 准备存放 token 的容器（荷载）
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, userId);
        return generateToken(claims); // 增加其它信息（ 本类内新建方法）
    }

    /**
     * 2.根据荷载生成 JWT token(私有方法，供本类方法调用)
     *
     * @param claims : 荷载
     * @return : 返回JWT token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 荷载
                .setClaims(claims)
                .setExpiration(generateExpirationDate()) // 过期时间，需要类型转换（本类内新建方法）
                // 签名,签名算法使用HS512
                .signWith(SignatureAlgorithm.HS512, systemConfig.getJwtSecretKey())
                .compact();
    }

    /**
     * 3. 生成 token 失效时间(私有方法，供本类方法调用)
     * 类型转换
     *
     * @return : 返回失效时间
     */
    private Date generateExpirationDate() {
        // 失效时间为：当前时间 + 配置的过期时间
        return new Date(System.currentTimeMillis() + Long.parseLong(systemConfig.getJwtExpirationTime()) * 1000);
    }

    /**
     * 4. ☑️ 从 token 中获取id
     *
     * @param token : Sting类型的token
     * @return : 返回id
     */
    public String getUserIdFromToken(String token) {
        // 登录用户id
        String id;
        try {
            Claims claims = getClaimsFormToken(token); // 根据 token 获取荷载（ 本类内新建方法 ）
            // 通过荷载调用 getSubject 方法，获取id
            id = claims.getId();
        } catch (Exception e) {
            // 有异常 id 为空
            id = null;
        }
        return id;
    }

    /**
     * 5. 从 token 中获取荷载
     *
     * @param token : Sting类型的token
     * @return : 返回claims(荷载)
     */
    private Claims getClaimsFormToken(String token) {
        // 荷载
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    // 密钥
                    .setSigningKey(systemConfig.getJwtSecretKey())
                    // 签名
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 6. ☑️ 验证 token 是否有效
     * 判断 token 是否已过期；
     *
     * @param token : Sting类型的token
     * @return : 返回 token 未失效的布尔值
     */
    public boolean validateToken(String token) {
        return isTokenExpired(token); // 本类内新建方法
    }

    /**
     * 7. 判断 token 是否失效
     *
     * @param token : Sting类型的token
     * @return : 返回 token 是否失效的布尔值
     */
    private boolean isTokenExpired(String token) {
        // 获取 token 失效时间，本类内新建此方法
        Date expireDate = getExpiredDateFromToken(token);
        System.out.println(expireDate);
        // 如果 token 过期时间在当前时间前面，有效
        return !expireDate.before(new Date());
    }

    /**
     * 8.  从 token 中获取过期时间
     *
     * @param token : Sting类型的token
     * @return : 返回过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        // 根据 token 获取荷载
        Claims claims = getClaimsFormToken(token);
        // 从荷载中获取过期时间
        return claims.getExpiration();
    }

    /**
     * 9. ☑️ 判断 token 是否可以被刷新
     * 过期了，可以刷新。获取有效时间方法取反为过期
     *
     * @param token : Sting类型的token
     * @return : 返回 token 是否失效的布尔值取反
     */
    public boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * 10. ☑️ 刷新 token 过期时间
     *
     * @param token : Sting类型的token
     * @return : 返回 token
     */
    public String refreshToken(String token) {
        // 获取荷载
        Claims claims = getClaimsFormToken(token);
        // 生成 token
        return generateToken(claims);
    }
}
