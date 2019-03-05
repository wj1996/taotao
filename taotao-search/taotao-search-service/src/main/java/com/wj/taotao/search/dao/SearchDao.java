package com.wj.taotao.search.dao;

import com.wj.taotao.common.SearchItem;
import com.wj.taotao.common.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 由于这个搜索功能只在搜索工程中使用，所以不提供相应的接口
 */
@Repository
public class SearchDao {

    @Autowired
    private HttpSolrClient solrClient;

    public SearchResult search(SolrQuery solrQuery) throws Exception{
        QueryResponse queryResponse = solrClient.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();
        List<SearchItem> list = new ArrayList<>();
        for(SolrDocument solrDocument : results){
            SearchItem searchItem = new SearchItem();
            searchItem.setId(Long.valueOf((String)solrDocument.get("id")));
//            searchItem.setTitle((String) solrDocument.get("item_title"));
            searchItem.setItem_desc((String) solrDocument.get("item_content"));
            //高亮显示
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            //有高亮显示的内容时。
            if (list2 != null && list2.size() > 0) {
                itemTitle = list2.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            searchItem.setTitle(itemTitle);

            list.add(searchItem);
        }
        SearchResult result = new SearchResult();
        result.setItemList(list);
        result.setRecordCount(results.getNumFound());
        return result;
    }
}
