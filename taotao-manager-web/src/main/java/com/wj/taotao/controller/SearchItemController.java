package com.wj.taotao.controller;

import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.search.service.ISearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemController {

    @Autowired
    private ISearchItemService searchItemService;

    @RequestMapping("index/importall")
    @ResponseBody
    public TaotaoResult importAllItems(){
        try {
            return searchItemService.importAllItems();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,"导入数据失败");
        }
    }
}
