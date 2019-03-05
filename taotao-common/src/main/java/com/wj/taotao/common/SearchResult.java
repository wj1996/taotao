package com.wj.taotao.common;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {

    private List<SearchItem> itemList;//搜索结果列表
    private Long recordCount;//总记录数
    private Long pageCount;//总页数


    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}
