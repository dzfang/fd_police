package com.soft.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class HttpSorlClientUtil {
	private static HttpSorlClientUtil sorlClient = null;

	public static HttpSorlClientUtil getInstance() {
		if (sorlClient == null) {
			sorlClient = new HttpSorlClientUtil();
		}
		return sorlClient;
	}

	public void insert(Map<String, String> para) {
		HttpSolrClient solrServer = new HttpSolrClient.Builder(Config.getSorlUrl()).build();
		SolrInputDocument document = new SolrInputDocument();
		for (String key : para.keySet()) {
			document.addField(key, para.get(key));
		}
		try {
			solrServer.add(document);
			solrServer.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test(){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id", "c0007");
		para.put("caseName", "从无间地狱归来再次获得重生机会，不料转生到修仙游戏世界。");
		HttpSorlClientUtil clientUtil = HttpSorlClientUtil.getInstance();
		clientUtil.insert(para);
	}
}
