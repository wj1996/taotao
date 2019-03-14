package com.wj.taotao.search.service;

import com.wj.taotao.common.SearchResult;
import com.wj.taotao.common.TaotaoResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface ISearchItemService {

    public TaotaoResult importAllItems() throws Exception;

    SearchResult search(String queryString, int page, int rows) throws Exception;

    public TaotaoResult updateItemById(Long itemId) throws Exception;
}
