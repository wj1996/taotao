package com.wj.taotao.service.impl;

import com.wj.taotao.mapper.TestMapper;
import com.wj.taotao.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    private TestMapper testMapper;

    @Override
    public String queryNow() {
        return testMapper.queryNow();
    }
}
