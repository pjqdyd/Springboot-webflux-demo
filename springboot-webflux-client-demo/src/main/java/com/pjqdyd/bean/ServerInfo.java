package com.pjqdyd.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**   
 * @Description:  [服务信息的bean]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {

    //服务url
    private String url;

}
