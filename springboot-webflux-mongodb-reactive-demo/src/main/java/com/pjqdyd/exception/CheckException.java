package com.pjqdyd.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**   
 * @Description:  [自定义异常 校验异常]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Data
@AllArgsConstructor
public class CheckException extends RuntimeException{

    private Integer code;

    private String message;

}
