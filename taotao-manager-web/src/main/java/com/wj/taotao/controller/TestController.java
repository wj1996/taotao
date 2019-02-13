package com.wj.taotao.controller;

import com.wj.taotao.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping("/test/queryNow")
    @ResponseBody
    private String queryNow(){
        return testService.queryNow();
    }
}
