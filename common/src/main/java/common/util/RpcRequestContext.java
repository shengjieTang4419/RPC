package common.util;

import java.io.Serializable;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 11:06
 * @description：消息请求Context类 封装了请求的class method 以及args
 * @modified By：
 * @version: $
 */
public class RpcRequestContext implements Serializable {
    private static final long serialVersionUID = -3921744138755819967L;
    private String clazzName;
    private String methodName;
    private Object[] args;
    private String version;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
