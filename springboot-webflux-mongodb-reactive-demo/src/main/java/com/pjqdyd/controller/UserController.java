package com.pjqdyd.controller;

import com.pjqdyd.entity.User;
import com.pjqdyd.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *    
 *
 * @Description:  [用户Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 返回所有用户集合
     *
     * @return
     */
    @GetMapping("/all")
    public Flux<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * 响应式流sse多次返回用户结合
     *
     * @return
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUserByStream() {
        return userRepository.findAll();
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Mono<User> createUser(@RequestBody User user) {
        //新增和修改都是save, 根据实际情况置空id
        //user.setId(null);
        return userRepository.save(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        //deleteById没有返回值, 不能判断是数据是否存在
        //所以要查询再删除, 返回响应实体
        //flatMap是操作数据, 返回Mono, 如果不操作数据,只是转换数据用户Map
        return userRepository.findById(id)
                .flatMap(user -> userRepository.delete(user)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 更新用户信息
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<User>> updateUser(
            @PathVariable("id") String id,
            @RequestBody User user) {
        return userRepository.findById(id).flatMap(u -> {
            u.setAge(user.getAge());
            u.setName(user.getName());
            return userRepository.save(u);
        })
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id){
        return userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
