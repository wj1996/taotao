package com.taotao.sso.service;

import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.pojo.TbUser;

public interface IUserRegisterService {

    public TaotaoResult checkData(String param, Integer type);

    public TaotaoResult register(TbUser user);
}
