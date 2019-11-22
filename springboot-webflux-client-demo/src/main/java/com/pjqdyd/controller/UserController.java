package com.pjqdyd.controller;

import com.pjqdyd.entity.User;
import com.pjqdyd.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**   
 * @Description:  [用户Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/all")
    public Flux<User> findAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/findById/{id}")
    public Mono<User> findUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }
}
