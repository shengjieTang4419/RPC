package common.handler;

import common.transport.RpcNetTransport;
import common.util.RpcRequestContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 16:22
 * @description：代理类的具体实现
 * @modified By：
 * @version: $
 */
public class RemoteInvationHandler implements InvocationHandler {
    private String host;
    private Integer port;
    private String version;

    public RemoteInvationHandler(String host, Integer port, String version) {
        this.host = host;
        this.port = port;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequestContext context = new RpcRequestContext();
        context.setClazzName(method.getDeclaringClass().getName());
        context.setMethodName(method.getName());
        context.setArgs(args);
        context.setVersion(version);
        RpcNetTransport transport = new RpcNetTransport(host,port);
        return transport.transportSend(context);
    }
}
