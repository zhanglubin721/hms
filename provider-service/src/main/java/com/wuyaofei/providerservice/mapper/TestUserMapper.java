package com.wuyaofei.providerservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestUserMapper {
    String selectUsernameById(@Param("id") Long id);
}
