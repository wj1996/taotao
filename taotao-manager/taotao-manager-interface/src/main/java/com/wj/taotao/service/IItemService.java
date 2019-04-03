package com.wj.taotao.service;

import com.wj.taotao.common.EasyUIDataGridResult;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.pojo.TbItem;

public interface IItemService {

    EasyUIDataGridResult getItemList(int page,int rows);

    TaotaoResult saveItem(TbItem tbItem, String desc);

    TbItem getItemById(Long itemId);
}
