package com.taotao.sso.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.taotao.sso.service.IUserLoginService;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.common.util.JsonUtils;
import com.wj.taotao.mapper.TbUserMapper;
import com.wj.taotao.pojo.TbUser;
import com.wj.taotao.pojo.TbUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

@Service
public class UserLoginServiceImpl implements IUserLoginService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private Jedis jedisClient;

    @Value("${USER_INFO}")
    private String USER_INFO;
    @Value(("${EXPIRE_TIME}"))
    private int EXPIRE_TIME;

    @Override
    public TaotaoResult login(String username, String password) {

        if(StringUtil.isEmpty(username) || StringUtils.isEmpty(password)) {
            return TaotaoResult.build(400,"用户名或密码错误");
        }

        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = userMapper.selectByExample(tbUserExample);
        if(tbUsers == null && tbUsers.size() == 0) {
            return TaotaoResult.build(400,"用户不存在");
        }
        TbUser user = tbUsers.get(0);
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return TaotaoResult.build(400,"用户名或密码不正确");
        }
        //校验成功，生成token：UUID生成，还需要设置token的有效性来模拟session，用户的数据放在Redis
        String token = UUID.randomUUID().toString();
        //存放用户数据到Redis中，使用jedis
        jedisClient.set(USER_INFO + token, JsonUtils.objectToJson(user));
        jedisClient.expire(USER_INFO + token,EXPIRE_TIME);
        //把token设置cookie当中，在表现层中设置
        return TaotaoResult.ok(token);
    }
}
