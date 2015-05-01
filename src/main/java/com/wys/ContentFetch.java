package com.wys;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ContentFetch extends Thread implements ParseStrategy{
	
	private List<String> contentList;
	private static CrawlerIface crawler=CrawlerFactory.getInstance();
	public ContentFetch(List<String> contentList){
		this.contentList=contentList;
	}
	public void run(){
		
	}
	public void parse(String url) {
		Document doc=crawler.getDocument(url, "utf8");
		Elements eles=doc.getElementsByClass("title");
		if(eles.isEmpty())
			return ;
		Element titleEle=eles.get(0);
		String titleStr=titleEle.text();
		Element dataEle=(Element)titleEle.parent().nextSibling().childNode(0).childNode(0).childNode(0);
		String dataStr=dataEle.text();
		Elements celes=doc.getElementsByClass("content");
		if(celes.isEmpty())
			return ;
		StringBuilder sb=new StringBuilder("");
		Elements contents=celes.get(0).children();
		for(Element e:contents){
			sb.append(e.text());
		}
		String contentStr=sb.toString();
	}

}
