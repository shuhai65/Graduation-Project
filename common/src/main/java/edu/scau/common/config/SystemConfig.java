package edu.scau.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SystemConfig {
    @Value("${jwt.secret_key}")
    private String jwtSecretKey;
    @Value("${jwt.expiration_time}")
    private String jwtExpirationTime;
    @Value("${token_exclude.path}")
    private List<String> excludePathPatterns;
}
