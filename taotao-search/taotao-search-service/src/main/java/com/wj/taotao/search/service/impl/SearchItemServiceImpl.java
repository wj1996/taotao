package com.wj.taotao.search.service.impl;

import com.wj.taotao.common.SearchItem;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.search.mapper.SearchItemMapper;
import com.wj.taotao.search.service.ISearchItemService;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchItemServiceImpl implements ISearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private HttpSolrClient httpSolrClient;

    @Override
    public TaotaoResult importAllItems() throws Exception {
        //查询所有商品
        List<SearchItem> searchItemList = searchItemMapper.getSearchItemList();
        if(null != searchItemList){
            for(SearchItem searchItem : searchItemList){
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id",searchItem.getId());
                doc.addField("item_title",searchItem.getTitle());
                doc.addField("item_content",searchItem.getItem_desc());
                //像索引中添加内容
                httpSolrClient.add(doc);
            }
        }
        httpSolrClient.commit();
        httpSolrClient.close();
        return TaotaoResult.ok();
    }
}
