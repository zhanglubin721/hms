package com.wuyaofei.providerservice.service.impl;

import com.wuyaofei.providerservice.mapper.TestUserMapper;
import com.wuyaofei.providerservice.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    public String findUsernameById(Long id) {
        return testUserMapper.selectUsernameById(id);
    }
}
