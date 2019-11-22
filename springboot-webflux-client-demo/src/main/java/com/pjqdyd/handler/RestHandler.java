package com.pjqdyd.handler;

import com.pjqdyd.bean.MethodInfo;
import com.pjqdyd.bean.ServerInfo;

/**   
 * @Description:  [Rest请求调用Handler]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface RestHandler {

    //初始服务信息
    void init(ServerInfo serverInfo);

    //根据方法信息调用Rest请求
    Object invokeRest(MethodInfo methodInfo);

}
