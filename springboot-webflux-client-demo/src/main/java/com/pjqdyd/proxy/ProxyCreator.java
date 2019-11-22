package com.pjqdyd.proxy;

/**   
 * @Description:  [创建代理类接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ProxyCreator {

    /**
     * 创建代理类
     * @param type
     * @return
     */
    Object createProxy(Class<?> type);

}
