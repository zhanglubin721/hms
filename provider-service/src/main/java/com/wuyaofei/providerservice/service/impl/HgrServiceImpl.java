package com.wuyaofei.providerservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuyaofei.providerservice.mapper.HgrMapper;
import com.wuyaofei.providerservice.pojo.Hgr;
import com.wuyaofei.providerservice.pojo.Order;
import com.wuyaofei.providerservice.pojo.PageResult;
import com.wuyaofei.providerservice.pojo.QueryPageBean;
import com.wuyaofei.providerservice.service.HgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HgrServiceImpl implements HgrService {

    @Autowired
    private HgrMapper hgrMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询数据库
        Page<Hgr> page = hgrMapper.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(Long rid) {
        hgrMapper.deleteByRid(rid);
    }

    @Override
    public void add(Hgr hgr) {
        hgrMapper.insertHgr(hgr);
    }

    @Override
    public void edit(Hgr hgr) {
        hgrMapper.edit(hgr);
    }

    @Override
    public Hgr findByRid(Long rid) {
        return hgrMapper.findByRid(rid);
    }

    @Override
    public void chooseHgr(Order order) {
        hgrMapper.insertOrder(order);
    }

    @Override
    public PageResult chooseFindPage(QueryPageBean queryPageBean) {
        //使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询数据库
        String day = String.valueOf(LocalDateTime.now().getDayOfYear());
        String year = String.valueOf(LocalDateTime.now().getYear());
        String time = year + day;
        Page<Hgr> page = hgrMapper.chooseFindPage(queryPageBean.getQueryString(), time);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
