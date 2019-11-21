package com.pjqdyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**   
 * @Description:  [webflux 整合 mongodb 响应数据]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@SpringBootApplication
@EnableMongoRepositories
public class SpringBootWebfluxMongodbReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxMongodbReactiveApplication.class, args);
    }

}
