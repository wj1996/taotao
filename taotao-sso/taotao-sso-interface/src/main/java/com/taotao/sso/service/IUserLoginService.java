package com.taotao.sso.service;

import com.wj.taotao.common.TaotaoResult;

public interface IUserLoginService {


    TaotaoResult login(String username,String password);

    /**
     * 根据token获取用户的信息，TaotaoResult包含用户的信息
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);
}
