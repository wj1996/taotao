package com.wj.taotao.search.mapper;

import com.wj.taotao.common.SearchItem;
import com.wj.taotao.common.SearchResult;

import java.util.List;

public interface SearchItemMapper {

    List<SearchItem> getSearchItemList();

    SearchItem getSearchItemByItemId(Long itemId);
}
