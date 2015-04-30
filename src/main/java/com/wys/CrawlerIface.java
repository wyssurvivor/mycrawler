package com.wys;

import org.jsoup.nodes.Document;
public interface CrawlerIface {
	public Document getDocument(String url);
	public Document getDocument(String url,String charCode);
}
