import bean.BusinessPO;
import common.proxy.RpcProxyClient;
import service.IBusinessService;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 18:14
 * @description：
 * @modified By：
 * @version: $
 */
public class ClientApp {
    public static void main(String[] args) {
        RpcProxyClient proxyClient = new RpcProxyClient();
        IBusinessService service = proxyClient.clienTProxy(IBusinessService.class,"127.0.0.1",9876,"2.0");
        System.out.println(service.createPO().toString());
        //service.print(new BusinessPO(2L,"111","test2","1"));
    }
}
