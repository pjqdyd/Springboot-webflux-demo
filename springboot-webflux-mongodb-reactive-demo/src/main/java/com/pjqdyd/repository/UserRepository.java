package com.pjqdyd.repository;

import com.pjqdyd.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**   
 * @Description:  [User Repository层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
