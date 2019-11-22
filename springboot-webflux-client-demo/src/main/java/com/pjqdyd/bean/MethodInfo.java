package com.pjqdyd.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 方法调用信息类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {

    //请求的url
    private String url;

    //请求的方法
    private HttpMethod httpMethod;

    //请求的参数
    private Map<String, Object> params;

    //请求的body
    private Mono body;

    //请求的body类型
    private Class<?> requestBodyType;

    //返回的是Flux (true)还是Mono (false)
    private boolean isFlux;

    //返回的对象类型
    private Class<?> returnElementType;

}
