package com.spring.boot;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类
 *
 * @author 代码的路
 * @date 2022/11/24
 */

@SpringBootApplication
@EnableApolloConfig
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

