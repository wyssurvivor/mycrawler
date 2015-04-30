package com.wys;

public class CrawlerFactory {
	public static CrawlerIface getInstance(){
		return new SsdutCrawler();
	}
}
