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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

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
        return TaotaoResult.ok();
    }
}
