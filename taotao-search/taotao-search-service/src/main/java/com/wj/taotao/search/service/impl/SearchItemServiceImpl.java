package com.wj.taotao.search.service.impl;

import com.wj.taotao.common.SearchItem;
import com.wj.taotao.common.SearchResult;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.search.dao.SearchDao;
import com.wj.taotao.search.mapper.SearchItemMapper;
import com.wj.taotao.search.service.ISearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchItemServiceImpl implements ISearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private HttpSolrClient httpSolrClient;

    /*@Autowired
    private ConcurrentUpdateSolrClient solrClient;*/

    /*@Autowired
    private HttpSolrServer httpSolrServer;*/
    @Autowired
    private SearchDao searchDao;

    @Override
    public TaotaoResult importAllItems() throws Exception {
        //查询所有商品
        try{
            long start = System.currentTimeMillis();
            System.out.println(start);
            List<SearchItem> searchItemList = searchItemMapper.getSearchItemList();
            System.out.println("query:" + System.currentTimeMillis());
            start = System.currentTimeMillis();
            if(null != searchItemList){
                for(SearchItem searchItem : searchItemList){
                   /* SolrInputDocument doc = new SolrInputDocument();
                    doc.addField("id",searchItem.getId());
                    doc.addField("item_title",searchItem.getTitle());
                    doc.addField("item_content",searchItem.getItem_desc());*/
                    //像索引中添加内容
//                    httpSolrClient.add(doc);
//                    httpSolrServer.add(doc);
                    httpSolrClient.addBean(searchItem);
                }
//                httpSolrClient.addBeans(searchItemList);
                httpSolrClient.commit();
            }

//            httpSolrServer.commit();
            /*httpSolrClient.commit();
            httpSolrClient.close();*/
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            return TaotaoResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(queryString);
        //设置分页
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        //指定默认搜索域
        solrQuery.set("df","item_title");
        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");
        SearchResult searchResult = searchDao.search(solrQuery);
        Long recordCount = searchResult.getRecordCount();
        Long pageCount = recordCount / rows;
        if(recordCount % rows > 0){
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        return searchResult;
    }

    @Override
    public TaotaoResult updateItemById(Long itemId) throws Exception {
        return searchDao.upItemById(itemId);
    }
}
