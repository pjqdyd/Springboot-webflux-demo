package com.pjqdyd.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**   
 * @Description:  [测试路由]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Configuration
public class TestRouter {

    /**
     * 单路由
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route(
                RequestPredicates.GET("/sayHiByRoute"), request -> {
                    Mono<String> result = Mono.just("hello!");
                    return ServerResponse.ok().body(result, String.class);
                }
        );
    }

}
