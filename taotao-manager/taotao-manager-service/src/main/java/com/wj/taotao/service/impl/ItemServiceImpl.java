package com.wj.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.taotao.common.EasyUIDataGridResult;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.common.util.IDUtils;
import com.wj.taotao.mapper.TbItemDescMapper;
import com.wj.taotao.mapper.TbItemMapper;
import com.wj.taotao.pojo.TbItem;
import com.wj.taotao.pojo.TbItemDesc;
import com.wj.taotao.pojo.TbItemExample;
import com.wj.taotao.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    /**
     * 增加当发送消息逻辑
     */
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination topicDestination;

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.offsetPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult saveItem(TbItem tbItem, String desc) {
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //商品状态，1：正常，2：下架，3：删除
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        tbItemMapper.insert(tbItem);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDescMapper.insert(tbItemDesc);
        //消息发送
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                return textMessage;
            }
        });
        return TaotaoResult.ok();
    }

    @Override
    public TbItem getItemById(Long itemId) {
        TbItemExample tbExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbExample.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbExample);
        if (null != tbItems && tbItems.size() > 0) return tbItems.get(0);
        return null;
    }
}
