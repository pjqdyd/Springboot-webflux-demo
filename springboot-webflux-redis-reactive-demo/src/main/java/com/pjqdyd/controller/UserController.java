package com.pjqdyd.controller;

import com.pjqdyd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**   
 * @Description:  [用户Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@RestController
@RequestMapping("/user")
public class UserController {

    //注入支持redis响应操作的ops
    private final ReactiveRedisOperations<String, User> userReactiveRedisOperations;

    public UserController(ReactiveRedisOperations<String, User> userReactiveRedisOperations) {
        this.userReactiveRedisOperations = userReactiveRedisOperations;
    }

    @GetMapping("/all")
    public Flux<User> getAll(){
        return userReactiveRedisOperations.keys("*").flatMap(userReactiveRedisOperations.opsForValue()::get);
    }

    @GetMapping("/findById/{id}")
    public Mono<User> findById(@PathVariable("id") String id){
        return userReactiveRedisOperations.opsForValue().get(id);
    }
}
