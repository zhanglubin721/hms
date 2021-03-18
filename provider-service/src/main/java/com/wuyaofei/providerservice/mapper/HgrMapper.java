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
public interface HgrMapper {
    Page<Hgr> findPage(@Param("queryString") String queryString);

    void deleteByRid(@Param("rid") Long rid);

    void insertHgr(Hgr hgr);

    void edit(Hgr hgr);

    Hgr findByRid(@Param("rid") Long rid);

    void insertOrder(Order order);

    Page<Hgr> chooseFindPage(@Param("queryString") String queryString, @Param("time") String time);
}
