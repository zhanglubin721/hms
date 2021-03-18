package com.wuyaofei.providerservice.service;

import com.wuyaofei.providerservice.pojo.Hgr;
import com.wuyaofei.providerservice.pojo.Order;
import com.wuyaofei.providerservice.pojo.PageResult;
import com.wuyaofei.providerservice.pojo.QueryPageBean;

public interface HgrService {
    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Long rid);

    void add(Hgr hgr);

    void edit(Hgr hgr);

    Hgr findByRid(Long rid);

    void chooseHgr(Order order);

    PageResult chooseFindPage(QueryPageBean queryPageBean);
}
