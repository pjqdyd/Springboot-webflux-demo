package com.pjqdyd.proxy.impl;

import com.pjqdyd.annotation.ApiServer;
import com.pjqdyd.bean.MethodInfo;
import com.pjqdyd.bean.ServerInfo;
import com.pjqdyd.handler.RestHandler;
import com.pjqdyd.handler.impl.WebClientRestHandler;
import com.pjqdyd.proxy.ProxyCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**   
 * @Description:  [使用JDK的动态代理实现代理接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
public class JDKProxyCreatorImpl implements ProxyCreator {

    @Override
    public Object createProxy(Class<?> type) {

        //根据接口得到API服务器的信息
        ServerInfo serverInfo = extractServerInfo(type);
        log.info("serverInfo: {}", serverInfo);

        //给每个代理类一个RestHandler实现
        RestHandler restHandler = new WebClientRestHandler();
        restHandler.init(serverInfo); //初始服务信息

        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{type},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                        //根据方法和参数得到调用信息
                        MethodInfo methodInfo = extractMethodInfo(method, args);
                        log.info("methodInfo: {}", methodInfo);
                        //根据方法信息调用Rest
                        return restHandler.invokeRest(methodInfo);
                    }
                });
    }

    //获取服务信息的方法
    private ServerInfo extractServerInfo(Class<?> type){
        ServerInfo serverInfo = new ServerInfo();
        ApiServer anno = type.getAnnotation(ApiServer.class); //拿到注解信息, url信息
        serverInfo.setUrl(anno.value());
        return serverInfo;
    }

    //获取要调用的方法信息
    private MethodInfo extractMethodInfo(Method method, Object[] args) {
        MethodInfo methodInfo = new MethodInfo();
        Annotation[] annotations = method.getAnnotations(); //拿到所有的注解
        for (Annotation annotation: annotations){
            if (annotation instanceof GetMapping){
                GetMapping a = (GetMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setHttpMethod(HttpMethod.GET);
            }else if(annotation instanceof PostMapping) {
                PostMapping a = (PostMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setHttpMethod(HttpMethod.POST);
            }else if(annotation instanceof DeleteMapping) {
                DeleteMapping a = (DeleteMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setHttpMethod(HttpMethod.DELETE);
            }
        }

        Parameter[] parameters = method.getParameters(); //得到调用的参数
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        methodInfo.setParams(paramsMap);
        for (int i = 0; i < parameters.length; i++){
            //判断每个参数是否有注解@PathVariable
            PathVariable pathAnno = parameters[i].getAnnotation(PathVariable.class);
            if (pathAnno != null){
                paramsMap.put(pathAnno.value(), args[i]); //设置请求的参数
            }

            //判断是否带有 @RequestBody
            RequestBody requAnno = parameters[i].getAnnotation(RequestBody.class);
            if (requAnno != null){
                methodInfo.setBody((Mono<?>) args[i]); //设置请求的body
                Type type = parameters[i].getParameterizedType(); //设置请求的body类型
                Class<?> requestBodyType = (Class<?>) type;
                methodInfo.setRequestBodyType(requestBodyType);
            }
        }

        //判断并设置返回的流类型Flux or Mono
        boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
        methodInfo.setFlux(isFlux);

        //判断并设置返回的实际对象类型
        Type[] types = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments();
        Class<?> elementType = (Class<?>) types[0];
        methodInfo.setReturnElementType(elementType);

        return methodInfo;
    }
}
