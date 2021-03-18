package com.wuyaofei.providerservice.service;

import com.wuyaofei.providerservice.pojo.BillingInfo;
import com.wuyaofei.providerservice.pojo.Order;
import com.wuyaofei.providerservice.pojo.PageResult;
import com.wuyaofei.providerservice.pojo.QueryPageBean;

public interface OrderService {

    PageResult orderFindPage(QueryPageBean queryPageBean);

    PageResult settlementFindPage(QueryPageBean queryPageBean);

    Order findByOid(Long id);

    void settlement(BillingInfo billingInfo);
}
