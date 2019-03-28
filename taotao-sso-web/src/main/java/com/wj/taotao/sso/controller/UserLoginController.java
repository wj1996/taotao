package com.wj.taotao.sso.controller;

import com.taotao.sso.service.IUserLoginService;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.common.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserLoginController {

    @Autowired
    private IUserLoginService userLoginService;

    @Value("${TT_TOKEN_KEY}")
    private String TT_TOKEN_KEY;

    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username,String password, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = userLoginService.login(username, password);
        //需要设置token到cookie中去，可以使用工具类，cookie需要跨域
        if(result.getStatus() == 200){
            CookieUtils.setCookie(request,response,TT_TOKEN_KEY,result.getData().toString());
        }

        return result;
    }

    @RequestMapping(value="user/token/{token}",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult getUserByToken(@PathVariable String token) {
        return userLoginService.getUserByToken(token);
    }



}
