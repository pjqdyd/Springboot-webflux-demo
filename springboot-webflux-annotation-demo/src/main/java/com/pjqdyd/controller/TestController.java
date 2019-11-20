package com.pjqdyd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**   
 * @Description:  [测试Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@RestController
public class TestController {

    //传统方式
    @GetMapping("/sayHi")
    private String sayHi(){
        return "Hello!";
    }

    //webflux的返回Mono方式
    @GetMapping("/sayHi2")
    private Mono<String> sayHi2(){
        return Mono.just("Hello!");
    }

    //webflux的返回Mono方式
    @GetMapping("/sayHi3")
    private Mono<String> sayHi3(){
        Mono<String> result = Mono.fromSupplier(() -> "hello!");
        return result;
    }

    //webflux返回Flux方式(流)
    @GetMapping(value = "/sayHi4", produces = "text/event-stream")
    private Flux<String> sayHi4(){
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " Flux hello " + i;
        }));
        return result;
    }

}
