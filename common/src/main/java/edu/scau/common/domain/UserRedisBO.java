package edu.scau.common.domain;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserRedisBO implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * jwt token
     */
    private String jwtToken;
    /**
     * access_token
     */
    private String accessToken;
    /**
     * 过期时间
     */
    private Long expireTime;
}
