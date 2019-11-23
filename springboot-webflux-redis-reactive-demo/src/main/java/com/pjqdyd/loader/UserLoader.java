package com.pjqdyd.loader;

import com.pjqdyd.entity.User;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 *    
 *
 * @Description:  [用户初始redis数据的类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

@Component
public class UserLoader {

    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, User> userReactiveRedisOperations;

    public UserLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, User> userReactiveRedisOperations) {
        this.factory = factory;
        this.userReactiveRedisOperations = userReactiveRedisOperations;
    }

    /**
     * @PostConstruct 注解会在本类调用构造器初始化后调用, 并执行一次
     */
    @PostConstruct
    public void loadUserData() {
        //由于可能多次启动应用程序,因此删除以前执行存在的数据,使用flushAll()
        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
                //收集用户
                Flux.just("user1", "user2", "user3").map(name -> new User(UUID.randomUUID().toString(), name, 18))
                //保存用户
                .flatMap(user -> userReactiveRedisOperations.opsForValue().set(user.getId(), user)))
                //查询所有的key, 并按照key ops.get()获取所有元素, 再订阅打印出来
                .thenMany(userReactiveRedisOperations.keys("*").flatMap(userReactiveRedisOperations.opsForValue()::get))
                .subscribe(System.out::println);
    }
}
