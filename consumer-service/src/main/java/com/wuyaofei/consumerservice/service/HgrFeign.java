package com.wuyaofei.consumerservice.service;

import com.wuyaofei.consumerservice.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "provider-service")
public interface HgrFeign {

    @GetMapping("/hgr/findPage")
    PageResult findPage(QueryPageBean queryPageBean);

    @GetMapping("/hgr/chooseFindPage")
    PageResult chooseFindPage(QueryPageBean queryPageBean);

    @GetMapping("/hgr/findByRid")
    Hgr findByRid(@RequestParam("rid") Long rid);

    @DeleteMapping("/hgr/delete")
    void delete(@RequestParam("rid") Long rid);

    @PostMapping("/hgr/add")
    void add(@RequestBody Hgr hgr);

    @PutMapping("/hgr/edit")
    void edit(@RequestBody Hgr hgr);

    @PostMapping("/hgr/chooseHgr")
    void chooseHgr(@RequestBody Order order);

    @GetMapping("/order/orderFindPage")
    PageResult orderFindPage(QueryPageBean queryPageBean);

    @GetMapping("/order/settlementFindPage")
    PageResult settlementFindPage(QueryPageBean queryPageBean);

    @GetMapping("/order/findByOid")
    Order findByOid(@RequestParam("id") Long id);

    @PostMapping("/order/settlement")
    void settlement(BillingInfo billingInfo);
}
