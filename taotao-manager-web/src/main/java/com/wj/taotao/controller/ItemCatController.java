package com.wj.taotao.controller;

import com.wj.taotao.common.EasyUITreeNode;
import com.wj.taotao.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {

    @Autowired
    private IItemCatService itemCatService;

    @RequestMapping("item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getitemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return itemCatService.getCatList(parentId);
    }
}
