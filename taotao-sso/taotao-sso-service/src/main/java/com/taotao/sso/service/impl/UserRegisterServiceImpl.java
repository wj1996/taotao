package com.taotao.sso.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.taotao.sso.service.IUserRegisterService;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.mapper.TbUserMapper;
import com.wj.taotao.pojo.TbUser;
import com.wj.taotao.pojo.TbUserExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRegisterServiceImpl implements IUserRegisterService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TaotaoResult checkData(String param, Integer type) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        if(type == 1){
            if(StringUtils.isBlank(param)){
                return TaotaoResult.ok(false);
            }
            criteria.andUsernameEqualTo(param);
        }else if(type == 2){
            criteria.andPhoneNotEqualTo(param);
        }else if(type == 3){
            criteria.andEmailEqualTo(param);
        }else{
            return TaotaoResult.build(400,"非法参数");
        }

        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        if(null != list && list.size() > 0){
            return TaotaoResult.ok(false);
        }
        return TaotaoResult.ok(true);
    }
}
