package com.framework.serverimpl;

import bean.BusinessPO;
import common.annotation.Reference;
import service.IBusinessService;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 20:04
 * @description：测试多实现
 * @modified By：
 * @version: 1$
 */
@Reference(interfaceClass = IBusinessService.class,version = "2.0")
public class BusinessServiceImpl2 implements IBusinessService {
    @Override
    public BusinessPO createPO() {
        System.out.println("version 2.0");
        return new BusinessPO(1L,"000","TESTNAME","0") ;
    }

    @Override
    public void print(BusinessPO po) {
        System.out.println("version 2.0");
        System.out.println(po.toString());
    }
}
