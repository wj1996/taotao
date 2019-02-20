package com.wj.taotao.controller;

import com.wj.taotao.content.service.IContentService;
import com.wj.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private IContentService iContentService;

    @RequestMapping("query/list")
    @ResponseBody
    public List<TbContent> queryTbContentByCid(Long categoryId){
        return iContentService.getTbContentList(categoryId);
    }

}
