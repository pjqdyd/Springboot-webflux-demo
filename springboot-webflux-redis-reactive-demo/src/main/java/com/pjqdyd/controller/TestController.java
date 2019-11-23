package com.pjqdyd.controller;

import com.alibaba.fastjson.JSONObject;
import com.pjqdyd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**   
 * @Description:  [测试Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 注入响应式的ReactiveRedisTemplate
     */
    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @GetMapping("/")
    public Mono test(){
        Mono m = reactiveRedisTemplate.opsForValue().set("test", "hello!");
        m.subscribe(System.out::println);
        return reactiveRedisTemplate.opsForValue().get("test");
    }

    @GetMapping("/u")
    public Mono<User> findU(){
        User user = new User("9527", "小明", 20);
        reactiveRedisTemplate.opsForValue().set("u", JSONObject.toJSONString(user)).subscribe();
        return reactiveRedisTemplate.opsForValue().get("u");
    }
}
