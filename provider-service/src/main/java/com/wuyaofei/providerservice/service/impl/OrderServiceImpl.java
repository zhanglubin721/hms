package com.wuyaofei.providerservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuyaofei.providerservice.mapper.HgrMapper;
import com.wuyaofei.providerservice.mapper.OrderMapper;
import com.wuyaofei.providerservice.pojo.*;
import com.wuyaofei.providerservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageResult orderFindPage(QueryPageBean queryPageBean) {
        //使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询数据库
        Page<Order> page = orderMapper.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult settlementFindPage(QueryPageBean queryPageBean) {
        //使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询数据库
        Page<Order> page = orderMapper.settlementFindPage(queryPageBean.getQueryString(), LocalDateTime.now());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Order findByOid(Long id) {
        return orderMapper.findByOid(id);
    }

    @Override
    public void settlement(BillingInfo billingInfo) {
        orderMapper.settlement(billingInfo.getId());
    }
}
