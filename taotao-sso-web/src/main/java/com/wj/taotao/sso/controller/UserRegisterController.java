package com.wj.taotao.sso.controller;

import com.wj.taotao.common.TaotaoResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserRegisterController {

    @RequestMapping("user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param,@PathVariable Integer type) {

        return null;
    }
}
