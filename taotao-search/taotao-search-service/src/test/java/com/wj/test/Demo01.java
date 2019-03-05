package com.wj.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//solr 7.7.0 测试


public class Demo01 {

    //add
    @Test
    public void testInsert() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","123");
        document.addField("item_title","苹果手机");
        document.addField("item_content","不错");
        solrClient.add(document);
        solrClient.commit();
        solrClient.close();
    }
    //delete 通过ID删除
    @Test
    public void testDeleteById() throws Exception{
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        solrClient.deleteById("123");
        solrClient.commit();
        solrClient.close();
    }

    //根据查询删除
    @Test
    public void testDeleteByQueryResult() throws Exception{
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        solrClient.deleteByQuery("item_title:华为手机");  //这样写会删除所有，原因未明
        solrClient.commit();
        solrClient.close();
    }

    @Test
    public void testQuery() throws Exception{
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        for(SolrDocument solrDocument : results){
            System.out.println(solrDocument.get("id") + "," + solrDocument.get("item_title") + "," + solrDocument.get("item_content"));
        }

    }

    //查询带高亮显示
    @Test
    public void testQuery2() throws Exception{
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setQuery("item_title:华为手机");
        solrQuery.setQuery("手机");
        solrQuery.set("df","item_title");
        //开启高亮显示
        solrQuery.setHighlight(true);
        //高亮显示的域
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em color='red'>");
        solrQuery.setHighlightSimplePost("</em>");
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        for(SolrDocument solrDocument : results){
            System.out.println(solrDocument.get("id"));
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = null;
            if(null != list && list.size() > 0){
                itemTitle = list.get(0);
            }else {
                itemTitle = (String) solrDocument.get("item_title");
            }

            System.out.println(itemTitle);

        }
    }


    @After
    public void after(){

    }


}
