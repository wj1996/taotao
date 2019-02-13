package com.wj.taotao.controller;

import com.wj.taotao.common.EasyUIDataGridResult;
import com.wj.taotao.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @RequestMapping("list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        return itemService.getItemList(page,rows);
    }
}
