package com.pjqdyd.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

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

    @NotBlank
    private String name;

    @Range(min = 10, max = 100)
    private int age;

}
