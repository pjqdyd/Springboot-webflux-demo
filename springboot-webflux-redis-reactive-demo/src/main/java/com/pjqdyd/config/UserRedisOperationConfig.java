package com.pjqdyd.config;

import com.pjqdyd.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**   
 * @Description:  [使用支持响应式Redis操作的Spring Bean创建配置类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Configuration
public class UserRedisOperationConfig {

    @Bean
    ReactiveRedisOperations<String, User> userReactiveRedisOperations(ReactiveRedisConnectionFactory factory){
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, User> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, User> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

}
