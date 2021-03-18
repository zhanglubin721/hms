package com.wuyaofei.consumerservice.controller;

import com.wuyaofei.consumerservice.pojo.*;
import com.wuyaofei.consumerservice.service.HgrFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private HgrFeign hgrFeign;

    @GetMapping("/orderFindPage")
    public PageResult orderFindPage(QueryPageBean queryPageBean) {
        log.info("[订单信息-分页查询]data:{}", queryPageBean);
        try {
            return hgrFeign.orderFindPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L, Collections.emptyList());
        }
    }

    @GetMapping("/settlementFindPage")
    public PageResult settlementFindPage(QueryPageBean queryPageBean) {
        log.info("[结算-分页查询]data:{}", queryPageBean);
        try {
            return hgrFeign.settlementFindPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L, Collections.emptyList());
        }
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Long id) {
        log.info("[结算-根据id查询-分页查询]id:{}", id);
        try {
            Order order = hgrFeign.findByOid(id);
            BillingInfo billingInfo = new BillingInfo(
                    order.getId(), order.getRid(), order.getRname(),
                    order.getType(), order.getUname(), order.getUid(),
                    order.getUseTime(), order.getStareTime(), order.getEndTime(),
                    0);

            int i = dateDiff(order.getStareTime(), LocalDateTime.now());
            if (i < 1) {
                if (order.getType() == 1) {
                    billingInfo.setMoney(90);
                } else {
                    billingInfo.setMoney(100);
                }
            } else {
                if (order.getType() == 1) {
                    billingInfo.setMoney(i * 90);
                } else {
                    billingInfo.setMoney(i * 100);
                }
            }


            return new Result(true, MessageConst.QUERY_STUDENT_SUCCESS, billingInfo);
        } catch (RuntimeException e) {
            log.error("[结算-根据id查询-分页查询]异常", e);
            return new Result(false, MessageConst.QUERY_STUDENT_FAIL);
        }
    }

    @PostMapping("/settlement")
    public Result settlement(@RequestBody BillingInfo billingInfo) {
        log.info("[结算-分页查询]billingInfo:{}", billingInfo);
        try {
            hgrFeign.settlement(billingInfo);
            return new Result(true, MessageConst.EDIT_STUDENT_SUCCESS);
        } catch (Exception e) {
            log.error("[结算-分页查询]异常", e);
            return new Result(false, MessageConst.EDIT_STUDENT_FAIL);
        }
    }

    public int dateDiff(LocalDateTime dt1, LocalDateTime dt2) {
        //获取第一个时间点的时间戳对应的秒数
        long t1 = dt1.toEpochSecond(ZoneOffset.ofHours(0));
        //获取第一个时间点在是1970年1月1日后的第几天
        long day1 = t1 / (60 * 60 * 24);
        //获取第二个时间点的时间戳对应的秒数
        long t2 = dt2.toEpochSecond(ZoneOffset.ofHours(0));
        //获取第二个时间点在是1970年1月1日后的第几天
        long day2 = t2 / (60 * 60 * 24);
        //返回两个时间点的天数差
        return (int) (day2 - day1);
    }
}
