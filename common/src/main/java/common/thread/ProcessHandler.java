package common.thread;

import common.util.RpcRequestContext;
import common.util.Stream;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/14 22:43
 * @description：本身BIO的，将请求放置到CachePool中，进行管理
 * @modified By：
 * @version: $
 */
public class ProcessHandler implements Runnable {
    Socket socket;
    Map<String,Object> handlerMap;

    public ProcessHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //服务端接收到的消息
            RpcRequestContext requestContext =(RpcRequestContext)objectInputStream.readObject();
            Object result = invoke(requestContext);
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            Stream.closeStream(objectInputStream);
            Stream.closeStream(objectOutputStream);
        }
    }

    /**
     * 反射进行调用具体的服务
     * @param requestContext
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object invoke(RpcRequestContext requestContext) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String version = requestContext.getVersion();
        String key = requestContext.getClazzName();
        if(!StringUtils.isEmpty(version))key = key+version;

        Object service = handlerMap.get(key);
        if(service == null){
            throw new RuntimeException(requestContext.getClazzName()+" serviceimpl not found");
        }

        Object[] params = requestContext.getArgs();
        int paramsLength = params==null?0:params.length;
        Class<?>[] type = new Class[paramsLength];
        for (int i =0;i<paramsLength;i++){
            type[i]=params[i].getClass();
        }
        Class<?> clazz = Class.forName(requestContext.getClazzName());
        Method method = clazz.getMethod(requestContext.getMethodName(), type);
        return method.invoke(service, params);
    }
}
