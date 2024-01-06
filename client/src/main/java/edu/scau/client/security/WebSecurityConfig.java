package edu.scau.client.security;

import edu.scau.common.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    SystemConfig systemConfig;

    @Autowired
    private MyUnauthorizedHandler unauthorizedHandler;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(){
        return new JwtAuthenticationProvider();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        List<String> excludePathPatterns = systemConfig.getExcludePathPatterns();
        //由于使用的是JWT，这里不需要csrf防护
        httpSecurity.csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许对于网站静态资源的无授权访问
                // /error,/actuator,/swagger-ui.html,/swagger-resources,/webjars,/v2,/v3,/doc.html,/favicon.ico,/csrf,/image,/user
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html"
                ).permitAll()
                .antMatchers(HttpMethod.GET,
                        "/error",
                        "/actuator/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/v3/**",
                        "/doc.html",
                        "/favicon.ico",
                        "/csrf",
                        "/image/**"
                ).permitAll()
                //对登录注册允许匿名访问
                .antMatchers(HttpMethod.POST,
                        "/user/email/resetPassword",
                        "/user/login",
                        "/user/register",
                        "/user/resetPassword"
                ).permitAll()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //测试时全部运行访问.permitAll();
                .antMatchers("/test/**").permitAll()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        //使用自定义provider
        httpSecurity.authenticationProvider(jwtAuthenticationProvider());
        //添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler);

        return httpSecurity.build();
    }
}
