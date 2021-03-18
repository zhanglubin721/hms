package com.wuyaofei.providerservice.mapper;

import com.github.pagehelper.Page;
import com.wuyaofei.providerservice.pojo.Hgr;
import com.wuyaofei.providerservice.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Mapper
public interface OrderMapper {
    Page<Order> findPage(@Param("queryString") String queryString);

    Page<Order> settlementFindPage(@Param("queryString") String queryString, @Param("now") LocalDateTime now);

    Order findByOid(@Param("id") Long id);

    void settlement(@Param("id") Long id);
}
