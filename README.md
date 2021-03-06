
#### Springboot Webflux 异步的函数流编写web接口

#### 项目结构:

```
 ├─java-async-servlet-demo                  java异步servlet模块
 ├─springboot-webflux-annotation-demo       springboot webflux 注解方式模块
 ├─springboot-webflux-router-demo           springboot webflux 函数路由方式模块
 ├─springboot-webflux-redis-reactive-demo   webflux整合redis reactive实现数据流接口
 ├─springboot-webflux-mongodb-reactive-demo webflux整合mongodb reactive实现数据流接口
 ├─springboot-webflux-client-demo           响应式调用其他webflux服务的client模块(实现类似feign的远程调用)
 ├─.gitignore                               .gitignore文件
 └─pom.xml                                  父pom文件               
```

####  提示:

  1. Mono<T> 是一个专门的Publisher<T>，它最多发出一个项，表示0-1个异步序列
  
  2. Flux<T> 是一个标准Publisher<T>，表示0-N个异步序列
        
   当通过流返回给前端数据, Flux是通过[SSE](SSE.md)来实现数据
   向浏览器流式推动的.