package com.pjqdyd.utils;

import com.pjqdyd.exception.CheckException;

import java.util.stream.Stream;

/**   
 * @Description:  [自定义参数校验]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class CheckUtil {

    //非法的名字
    private static final String[] INVALID_NAMES = {"大爷", "fuck"};

    public static void checkName(String value){
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
                    throw new CheckException(505, "非法的名称:" + name);
        });
    }

}
