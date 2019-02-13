package com.wj.taotao.content.service.impl;

import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.common.util.JsonUtils;
import com.wj.taotao.content.jedis.JedisClient;
import com.wj.taotao.content.service.IContentService;
import com.wj.taotao.mapper.TbContentMapper;
import com.wj.taotao.pojo.TbContent;
import com.wj.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;


    @Override
    public List<TbContent> getTbContentList(Long cid) {

        //添加缓存不能影响正常的业务逻辑(所有涉及到缓存的业务操作必须try_catch处理异常)

        //先从缓存中获取数据
        try {
            String jsonStr = jedisClient.hget(CONTENT_KEY, cid + "");
            if(StringUtils.isNotBlank(jsonStr)){
                return JsonUtils.jsonToList(jsonStr,TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
        try {
            jedisClient.hset(CONTENT_KEY,cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TaotaoResult saveContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(tbContent.getCreated());
        tbContentMapper.insertSelective(tbContent);
        return TaotaoResult.ok();
    }
}
