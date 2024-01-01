package edu.scau.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configurable
@EnableTransactionManagement
@ComponentScan(basePackages = {"edu.scau.api", "edu.scau.client", "edu.scau.common"})
@MapperScan(basePackages = {"edu.scau.client.mapper"})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiApplication.class);
        application.run(args);
    }
}