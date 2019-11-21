package com.pjqdyd.repository;

import com.pjqdyd.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**   
 * @Description:  [User Repository层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByAgeBetween(int start, int end);

}
