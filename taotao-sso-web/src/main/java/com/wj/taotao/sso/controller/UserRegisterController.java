package com.wj.taotao.sso.controller;

import com.taotao.sso.service.IUserRegisterService;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRegisterController {


    @Autowired
    private IUserRegisterService userRegisterService;

    @RequestMapping("user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param,@PathVariable Integer type) {
        return userRegisterService.checkData(param,type);
    }

    @RequestMapping(value = "user/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user) {
        return userRegisterService.register(user);
    }

}
