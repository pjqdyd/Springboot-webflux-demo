package com.pjqdyd.service;

import com.pjqdyd.annotation.ApiServer;
import com.pjqdyd.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**   
 * @Description:  [用户服务接口, 调用其他服务]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@ApiServer("http://localhost:8080/user")
public interface UserService {

    @GetMapping("/all")
    Flux<User> getAllUser();

    @GetMapping("/findById/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @PostMapping("/save")
    Mono<User> createUser(@RequestBody Mono<User> userMono);

}
