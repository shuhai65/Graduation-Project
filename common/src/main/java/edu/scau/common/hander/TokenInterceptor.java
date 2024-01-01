package edu.scau.common.hander;

import com.alibaba.fastjson.JSON;
import edu.scau.common.config.SystemConfig;
import edu.scau.common.constant.RedisEnum;
import edu.scau.common.domain.UserRedisBO;
import edu.scau.common.exception.AccessDeniedException;
import edu.scau.common.utils.AuthUserContext;
import edu.scau.common.utils.JwtUtil;
import edu.scau.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    SystemConfig systemConfig;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return true if the execution chain should proceed with the next interceptor or the handler itself. Else, DispatcherServlet assumes that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前请求的路径
        String requestURI = request.getRequestURI();
        log.info(requestURI);
        // 判断是否是需要排除的路径
        List<String> excludePathPatterns = systemConfig.getExcludePathPatterns();
        //循环判断是否是需要排除的路径
        for (String excludePathPattern : excludePathPatterns) {
            if (requestURI.contains(excludePathPattern)) {
                log.warn("需要排除的路径");
                return true;
            }
        }
        // 在这里进行 Token 验证逻辑
        String token = request.getHeader("Authorization");
        // 验证 Token 的合法性，如果验证失败
        if (!isValidToken(token)) {
            AuthUserContext.clean();
            log.warn("token不合法");
            throw new AccessDeniedException("token不合法");
        }
        return true;
    }

    /**
     * 验证 Token 的合法性
     *
     * @param token Token
     * @return 是否合法
     */
    private boolean isValidToken(String token) {
        if (token != null) {
            try {
                String userIdFromToken = jwtUtil.getUserIdFromToken(token);
                String key = RedisEnum.LOGIN_USER.getValue() + userIdFromToken;
                //从redis获取用户信息
                String userRedisBOJSON = (String) redisUtil.get(key);
                if (userRedisBOJSON == null) {
                    log.info("用户在redis中不存在");
                    return false;
                }
                UserRedisBO userRedisBO = JSON.parseObject(userRedisBOJSON, UserRedisBO.class);
                if (!userRedisBO.getJwtToken().equals(token)) {
                    log.info("token不一致");
                    return false;
                }
                AuthUserContext.set(userRedisBO);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 这里可以对返回结果进行处理
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后的处理
    }
}
