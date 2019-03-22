package com.taotao.sso.service;

import com.wj.taotao.common.TaotaoResult;

public interface IUserLoginService {


    TaotaoResult login(String username,String password);
}
