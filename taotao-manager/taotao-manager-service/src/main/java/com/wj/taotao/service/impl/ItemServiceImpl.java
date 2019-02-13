package com.wj.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.taotao.common.EasyUIDataGridResult;
import com.wj.taotao.mapper.TbItemMapper;
import com.wj.taotao.pojo.TbItem;
import com.wj.taotao.pojo.TbItemExample;
import com.wj.taotao.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

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
}
