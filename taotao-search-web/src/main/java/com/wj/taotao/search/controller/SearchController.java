package com.wj.taotao.search.controller;

import com.wj.taotao.common.SearchResult;
import com.wj.taotao.search.service.ISearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;

    @Autowired
    private ISearchItemService searchItemService;

    @RequestMapping("search")
    public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue = "1") Integer page, Model model){
        try {
            SearchResult result = searchItemService.search(queryString, page,ITEM_ROWS);
            model.addAttribute("query",queryString);
            model.addAttribute("totalPages",result.getPageCount());
            model.addAttribute("itemList",result.getItemList());
            model.addAttribute("page",page);
            return "search";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
