package com.wj.test;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class Demo01 {

    @Test
    public void testInsert() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://10.0.0.119:8090/solr/collection1").build();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","123");
        document.addField("item_title","华为手机");
        document.addField("item_content","不错");
        solrClient.add(document);
        solrClient.commit();
        solrClient.close();
    }

}
