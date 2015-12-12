package com.lclizhao.sharebook;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.filter.JWTSecurityFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ApplicationPath;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:资源类的启动入口
 */
@ApplicationPath("/webapi/*")
public class AirResourceConfig extends ResourceConfig {
    public AirResourceConfig() throws Exception {
        register(JWTSecurityFilter.class);
        packages("com.lclizhao.sharebook.resource");
        register(RolesAllowedDynamicFeature.class);//角色控制
        register(MultiPartFeature.class);
//        register(KeyUtil.class);
//        final Key key= KeyUtil.getKey();
//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//
//                bind(key).to(Key.class);
//            }
//        });
        /**register(BookResource.class);**/
    }

}
