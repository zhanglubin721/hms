package com.wuyaofei.consumerservice.controller;

import com.wuyaofei.consumerservice.pojo.*;
import com.wuyaofei.consumerservice.service.HgrFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/hgr")
public class HgrController {

    @Autowired
    private HgrFeign hgrFeign;

    @LoadBalanced
    @GetMapping("/findPage")
    public PageResult findPage(QueryPageBean queryPageBean) {
        log.info("[客房信息-分页查询]data:{}", queryPageBean);
        try {
            return hgrFeign.findPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L, Collections.emptyList());
        }
    }

    @GetMapping("/chooseFindPage")
    public PageResult chooseFindPage(QueryPageBean queryPageBean) {
        log.info("[可订房信息-分页查询]data:{}", queryPageBean);
        try {
            return hgrFeign.chooseFindPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L, Collections.emptyList());
        }
    }

    @GetMapping("/findByRid")
    public Result findByRid(@RequestParam("rid") Long rid) {
        log.info("[客房信息-根据客房id查询]data:{}", rid);
        try {
            Hgr hgr = hgrFeign.findByRid(rid);
            return new Result(true, MessageConst.QUERY_COURSE_SUCCESS, hgr);
        } catch (RuntimeException e) {
            log.error("[客房信息-分页查询]异常", e);
            return new Result(false, MessageConst.QUERY_COURSE_FAIL);
        }
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("rid") Long rid) {
        log.debug("[要删除的客房id]cid:{}", rid);
        try {
            hgrFeign.delete(rid);
            return new Result(true, MessageConst.DELETE_COURSE_SUCCESS);
        } catch (RuntimeException e) {
            log.error("[客房信息-删除]异常", e);
            return new Result(false, MessageConst.DELETE_COURSE_FAIL);
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Hgr hgr) {
        log.info("[客房信息-新增]data:{}", hgr);
        try {
            hgrFeign.add(hgr);
            return new Result(true, MessageConst.ADD_COURSE_SUCCESS);
        } catch (RuntimeException e) {
            log.error("[客房信息-新增]", e);
            return new Result(false, MessageConst.ADD_COURSE_FAIL + "：" + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody Hgr hgr) {
        log.debug("[修改客房信息]hgr:{}", hgr);
        try {
            hgrFeign.edit(hgr);
            return new Result(true, MessageConst.EDIT_COURSE_SUCCESS);
        } catch (RuntimeException e) {
            log.error("[客房信息-重置]异常", e);
            return new Result(false, MessageConst.EDIT_COURSE_FAIL);
        }
    }

    @PostMapping("/chooseHgr")
    public Result chooseHgr(@RequestBody Order order) {
        try {
            //往order中添加今天日期以及预计退房日期
            LocalDateTime now = LocalDateTime.now();
            order.setStareTime(now);
            LocalDateTime endTime = now.plusDays(order.getUseTime());
            order.setEndTime(endTime);

            String rids = String.valueOf(order.getRid());
            String day = String.valueOf(now.getDayOfYear());
            String year = String.valueOf(now.getYear());
            StringBuffer idBuffer = new StringBuffer(String.valueOf(order.getUid()));
            String id = idBuffer.substring(idBuffer.length() - 4, idBuffer.length());
            order.setId(Long.parseLong(rids + year + day + id));
            order.setState(1);
            log.debug("生成的订单id是 id:{}", order.getId());

            hgrFeign.chooseHgr(order);
            return new Result(true, MessageConst.CHOOSE_COURSE_SUCCESS);
        } catch (Exception e) {
            log.error("[订房]异常", e);
            return new Result(false, MessageConst.CHOOSE_COURSE_FAIL);
        }
    }
}
