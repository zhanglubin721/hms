package com.wuyaofei.providerservice.controller;

import com.wuyaofei.providerservice.pojo.BillingInfo;
import com.wuyaofei.providerservice.pojo.Order;
import com.wuyaofei.providerservice.pojo.PageResult;
import com.wuyaofei.providerservice.pojo.QueryPageBean;
import com.wuyaofei.providerservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orderFindPage")
    public PageResult orderFindPage(@RequestBody QueryPageBean queryPageBean) {
        log.info("[客房信息-分页查询]data:{}", queryPageBean);
        return orderService.orderFindPage(queryPageBean);
    }

    @RequestMapping("/settlementFindPage")
    public PageResult settlementFindPage(@RequestBody QueryPageBean queryPageBean) {
        log.info("[结算-分页查询]data:{}", queryPageBean);
        return orderService.settlementFindPage(queryPageBean);
    }

    @GetMapping("/findByOid")
    public Order findByOid(@RequestParam("id") Long id) {
        log.info("[结算-根据id查询-分页查询]data:{}", id);
        return orderService.findByOid(id);
    }

    @PostMapping("/settlement")
    public void settlement(@RequestBody BillingInfo billingInfo) {
        orderService.settlement(billingInfo);
    }
}
