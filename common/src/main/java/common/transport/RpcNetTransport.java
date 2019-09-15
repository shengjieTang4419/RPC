package common.transport;

import common.util.RpcRequestContext;
import common.util.Stream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 16:36
 * @description：利用socket网络转发
 * @modified By：
 * @version: 1$
 */
public class RpcNetTransport {
    private String host;
    private Integer port;

    public RpcNetTransport(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Object transportSend(RpcRequestContext requestContext){
        Socket socket = null;
        Object result = null;
        ObjectOutputStream objectOutputStream= null;
        ObjectInputStream objectInputStream = null;
        try {
            //主机地址 端口号
            socket = new Socket(host,port);
            //先序列化后发送请求 进入服务端
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(requestContext);
            objectOutputStream.flush();
            //接收服务端信息并处理
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            Stream.closeStock(socket);
            Stream.closeStream(objectOutputStream);
            Stream.closeStream(objectInputStream);
        }

        return result;
    }
}
