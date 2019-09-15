package common.util;

import common.annotation.Reference;
import common.thread.ProcessHandler;
import javafx.fxml.Initializable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 18:45
 * @description: 入口类 依托于Spring管理
 * @modified By：
 * @version: 2$  重写Spring初始化容器
 */
@Component
public class RpcService implements ApplicationContextAware, InitializingBean {
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private final String configPath = "resources.config";
    int defaultPort=8080;

    Map<String,Object> handlerMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket stock=null;
        try {
            stock = new ServerSocket(getPort());
            //自旋去获取请求
            while(true){
                //accept 是属于阻塞的请求的，这里创建cache线程池，用于处理accept请求，将BIO的阻塞粒度降低到线程级别
                Socket acceptSocket = stock.accept();
                cachedThreadPool.submit(new ProcessHandler(acceptSocket,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Stream.closeStock(stock);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Reference.class);
        if(beansWithAnnotation!=null){
            for(Object serviceBean : beansWithAnnotation.values()){
                Reference annotation = serviceBean.getClass().getAnnotation(Reference.class);
                String name = annotation.interfaceClass().getName();
                String version = annotation.version();
                if(!StringUtils.isEmpty(version)) name = name + version;
                handlerMap.put(name,serviceBean);
            }
        }
    }

    /**
     * 获取配置端口号
     * @return
     */
    private Integer getPort(){
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Object port = bundle.getString("port");
        if(port !=null){return Integer.valueOf(String.valueOf(port));}
        return defaultPort;
    }
}
