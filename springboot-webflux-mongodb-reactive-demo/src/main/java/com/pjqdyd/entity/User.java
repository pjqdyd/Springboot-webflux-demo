package com.pjqdyd.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**   
 * @Description:  [User实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String name;

    private int age;

}
