package com.wj.taotao.controller;

import com.wj.taotao.common.EasyUITreeNode;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.content.service.IContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private IContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue = "0") Long parentId){
        return contentCategoryService.getContentCategoryList(parentId);
    }

    @RequestMapping("create")
    @ResponseBody
    public TaotaoResult createCategory(Long parentId,String name){
        return contentCategoryService.addContentCategory(parentId,name);
    }

}
