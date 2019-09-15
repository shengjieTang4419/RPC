package common.proxy;

import common.handler.RemoteInvationHandler;

import java.lang.reflect.Proxy;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 16:16
 * @description： 代理类 使用jdk动态代理 具体逻辑在RemoteInvationHandler
 * @modified By：
 * @version: $
 */
public class RpcProxyClient {
    public <T> T clienTProxy(final Class<T> interfaceClazz,final String host,final Integer port,final String version){
        return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(),new Class<?>[]{interfaceClazz}, new RemoteInvationHandler(host,port,version));
    }
}
