package com.pjqdyd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
 * @Description:  [用户实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private Integer age;

}
