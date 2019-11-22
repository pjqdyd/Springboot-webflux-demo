package com.pjqdyd.config;

import com.pjqdyd.proxy.ProxyCreator;
import com.pjqdyd.proxy.impl.JDKProxyCreatorImpl;
import com.pjqdyd.service.UserService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
 * @Description:  [Webflux Client配置类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Configuration
public class WebfluxClientConfig {

    @Bean
    public FactoryBean<UserService> userServiceFactoryBean(ProxyCreator proxyCreator){
        return new FactoryBean<UserService>() {

            //输出返回代理对象
            @Override
            public UserService getObject() throws Exception {
                return (UserService) proxyCreator.createProxy(this.getObjectType());
            }

            //注入容器的类型
            @Override
            public Class<?> getObjectType() {
                return UserService.class;
            }
        };

    }

    /**
     * 注入代理类创建器
     * @return
     */
    @Bean
    public ProxyCreator proxyCreator(){
        return new JDKProxyCreatorImpl();
    }

}
