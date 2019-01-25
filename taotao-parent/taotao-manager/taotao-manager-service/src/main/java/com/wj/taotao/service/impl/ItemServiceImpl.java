package com.wj.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.taotao.common.pojo.EasyUIDataGridResult;
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
        //设置分页信息
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();

        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(list);
        return easyUIDataGridResult;
    }
}
