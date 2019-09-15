package service;

import bean.BusinessPO;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/14 22:11
 * @description：接口
 * @modified By：
 * @version: 1$
 */
public interface IBusinessService {
    public BusinessPO createPO();

    public void print(BusinessPO po);
}
