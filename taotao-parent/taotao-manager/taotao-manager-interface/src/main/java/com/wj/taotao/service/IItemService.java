package com.wj.taotao.service;

import com.wj.taotao.common.pojo.EasyUIDataGridResult;

public interface IItemService {

    EasyUIDataGridResult getItemList(int page, int rows);
}
