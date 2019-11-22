package com.pjqdyd.handler.impl;

import com.pjqdyd.bean.MethodInfo;
import com.pjqdyd.bean.ServerInfo;
import com.pjqdyd.handler.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**   
 * @Description:  [web client rest 实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class WebClientRestHandler implements RestHandler {

    //初始Httpclient
    private WebClient webClient;

    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    //处理Rest请求
    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        Object result = null;
        WebClient.RequestBodySpec requestBodySpec = webClient  //定义请求
                .method(methodInfo.getHttpMethod())
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec responseSpec = null; //定义响应
        //判断是否带了请求body
        if (methodInfo.getBody() != null){
            //带body发出请求
            responseSpec = requestBodySpec.body(methodInfo.getBody(), methodInfo.getRequestBodyType()).retrieve();
        }else {
            //直接发出请求
            responseSpec = requestBodySpec.retrieve();
        }

        //判断是否有异常
        responseSpec.onStatus(status -> status.value() == 404,
                response -> Mono.just(new RuntimeException("Not Found")));

        if (methodInfo.isFlux()){ //如果返回的是Flux类型
            result = responseSpec.bodyToFlux(methodInfo.getReturnElementType());
        }else {
            result = responseSpec.bodyToMono(methodInfo.getReturnElementType());
        }
        return result;
    }
}
