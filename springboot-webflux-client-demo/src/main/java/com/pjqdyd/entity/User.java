package com.pjqdyd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**   
 * @Description:  [User实体类, 字段保持和ORM对象相同]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private int age;

}
