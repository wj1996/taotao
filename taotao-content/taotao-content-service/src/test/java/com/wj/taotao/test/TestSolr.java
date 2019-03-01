/*
package com.wj.taotao.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import java.io.IOException;

public class TestSolr {


   @Test
   public void testInsert() throws IOException, SolrServerException {
       //老版本写法（5.几版本之前）
//      HttpSolrClient solrClient = new HttpSolrClient("http://10.0.0.119:8090/solr");
//       HttpSolrClient.Builder solrClient = new HttpSolrClient.Builder().withBaseSolrUrl("http://10.0.0.119:8090/solr/collection1");
       HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
       SolrInputDocument doc = new SolrInputDocument();
       doc.addField("id","1");
       doc.addField("title","苹果手机");
       solrClient.add(doc);
       solrClient.commit();
       solrClient.close();
   }

   @Test
    public void testDelete() throws IOException, SolrServerException {
       HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        solrClient.deleteById("1");
        solrClient.commit();
        solrClient.close();
   }

   @Test
    public void testQuery() throws IOException, SolrServerException {
       HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
       SolrQuery solrQuery = new SolrQuery();
       solrQuery.setQuery("*:*");
       QueryResponse query = solrClient.query(solrQuery);
       SolrDocumentList results = query.getResults();
       for(SolrDocument solrDocument : results){
           System.out.println(solrDocument.get("id"));
           System.out.println(solrDocument.get("title"));
       }
       solrClient.close();
   }
}
*/
