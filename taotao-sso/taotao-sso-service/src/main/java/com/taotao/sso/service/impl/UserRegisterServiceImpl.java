package com.taotao.sso.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.taotao.sso.service.IUserRegisterService;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.mapper.TbUserMapper;
import com.wj.taotao.pojo.TbUser;
import com.wj.taotao.pojo.TbUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service
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

    @Override
    public TaotaoResult register(TbUser user) {
        if(StringUtils.isEmpty(user.getUsername())) {
            return TaotaoResult.build(400,"注册失败，用户名不能为空");
        }
        if(StringUtils.isEmpty(user.getPassword())) {
            return TaotaoResult.build(400,"注册失败，密码不能为空");
        }
        TaotaoResult result = checkData(user.getUsername(), 1);
        if(!(boolean) result.getData()) {
            return TaotaoResult.build(400,"注册失败，用户名已存在");
        }

        if(StringUtils.isNotEmpty(user.getPhone())) {
            TaotaoResult result2 = checkData(user.getPhone(), 2);
            if(!(boolean) result2.getData()) {
                return TaotaoResult.build(400,"注册失败，手机号码已被使用");
            }
        }

        if(StringUtils.isNotEmpty(user.getEmail())) {
            TaotaoResult result2 = checkData(user.getEmail(), 3);
            if(!(boolean) result2.getData()) {
                return TaotaoResult.build(400,"注册失败，邮箱已被使用");
            }
        }

        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        tbUserMapper.insertSelective(user);
        return TaotaoResult.ok();
    }
}
