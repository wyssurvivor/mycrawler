package com.wys;
import java.util.List;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
public class ContentFetch extends Thread implements ParseStrategy{
	private boolean isRunning=false;
	private List<String> contentList=new LinkedList<String>();
	private static CrawlerIface crawler=CrawlerFactory.getInstance();
	public ContentFetch(){}
	public ContentFetch(List<String> contentList){
		this.contentList=contentList;
	}
	public void run(){
		try{
			while(!contentList.isEmpty()){
				String url=contentList.remove(0);
				parse(url);
			}
		}finally{
			isRunning=false;
		}
	}
	public boolean insertContentPage(String url){
		if(url.isEmpty())
			return false;
		contentList.add(url);
		if(isRunning==false){
			synchronized(this){
				if(isRunning==false){
					isRunning=true;
					this.start();
				}
			}
		}
		return true;
	}
	public void parse(String url) {
		Document doc=crawler.getDocument(url, "utf8");
		Elements eles=doc.getElementsByClass("title");
		if(eles.isEmpty())
			return ;
		Element titleEle=eles.get(0);
		String titleStr=titleEle.text();
		System.out.println("title:"+titleStr);
		Element dataEle=(Element)titleEle.parent().nextSibling().nextSibling();
		String dataStr=dataEle.text();
		analyzeData(dataStr);
		Elements celes=doc.getElementsByClass("content");
		if(celes.isEmpty())
			return ;
		StringBuilder sb=new StringBuilder("");
		Elements contents=celes.get(0).children();
		for(Element e:contents){
			sb.append(e.text());
		}
		String contentStr=sb.toString();
		System.out.println("content:"+contentStr);
	}
	private void analyzeData(String dataStr){
		//dataStr=dataStr.replaceAll("\\s{1,}"," ");
		String[] strs=dataStr.split("¡¡¡¡");
		System.out.println("url:"+strs[0]);
		System.out.println("date:"+strs[1]);
		System.out.println("src:"+strs[2].substring(strs[2].indexOf(":")));
		System.out.println("count:"+strs[3].substring(strs[3].indexOf(":"), strs[3].lastIndexOf(" ")));
	}

}
