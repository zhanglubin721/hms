package com.wuyaofei.providerservice.controller;

import com.wuyaofei.providerservice.pojo.*;
import com.wuyaofei.providerservice.service.HgrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/hgr")
public class HgrController {

    @Autowired
    private HgrService hgrService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        log.info("[客房信息-分页查询]data:{}", queryPageBean);
        return hgrService.findPage(queryPageBean);
    }

    @RequestMapping("/chooseFindPage")
    public PageResult chooseFindPage(@RequestBody QueryPageBean queryPageBean) {
        log.info("[可订的客房信息-分页查询]data:{}", queryPageBean);
        return hgrService.chooseFindPage(queryPageBean);
    }

    @GetMapping("/findByRid")
    public Hgr findBySid(@RequestParam("rid") Long rid) {
        log.info("[客房信息-根据客房id查询]data:{}", rid);
        return hgrService.findByRid(rid);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("rid") Long rid) {
        log.debug("[要删除的客房id]cid:{}", rid);
        hgrService.delete(rid);
    }

    @PostMapping("/add")
    public void add(@RequestBody Hgr hgr) {
        log.info("[客房信息-新增]data:{}", hgr);
        hgrService.add(hgr);

    }

    @PutMapping("/edit")
    public void edit(@RequestBody Hgr hgr) {
        log.debug("[修改客房信息]hgr:{}", hgr);
        hgrService.edit(hgr);
    }

    @PostMapping("/chooseHgr")
    public void chooseHgr(@RequestBody Order order) {
        log.debug("[订房信息]hgr:{}", order);
        hgrService.chooseHgr(order);
    }
}
