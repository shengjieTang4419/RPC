package com.framework.serverimpl;

import bean.BusinessPO;
import common.annotation.Reference;
import service.IBusinessService;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/14 22:20
 * @description：实现类
 * @modified By：
 * @version: $
 */
@Reference(interfaceClass = IBusinessService.class,version = "1.0")
public class BusinessServiceImpl implements IBusinessService {

    public BusinessPO createPO() {
        System.out.println("version 1.0");
        return new BusinessPO(1L,"000","TESTNAME","0") ;
    }

    @Override
    public void print(BusinessPO po) {
        System.out.println("version 1.0");
        System.out.println(po.toString());
    }
}
