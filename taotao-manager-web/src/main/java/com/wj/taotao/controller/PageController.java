package com.wj.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }


    @RequestMapping("content")
    public String showContent(){
        return "content";
    }

    @RequestMapping("content-category")
    public String showContentCategory(){
        return "content-category";
    }
}
