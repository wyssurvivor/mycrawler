package com.wys;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.wys.bean.NewsContent;
import com.wys.dao.NewsContentDAO;
public class ContentFetch implements ParseStrategy{
	private boolean isRunning=false;
	private Queue<String> contentList=new ConcurrentLinkedQueue<String>();
	private static CrawlerIface crawler=CrawlerFactory.getInstance();
	private FetchThread thread=null;
	private Object lockObj=new Object();
	private NewsContentDAO newsDao=new NewsContentDAO();
	public ContentFetch(){}
	public ContentFetch(Queue<String> contentList){
		this.contentList=contentList;
	}
	private class FetchThread extends Thread{
		public void run(){
			try{
				while(!contentList.isEmpty()){
					String url=contentList.poll();
					parse(url);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				synchronized(lockObj){
					isRunning=false;
					thread=null;
				}
			}
		}
	}
	public boolean insertContentPage(String url){
		if(url.isEmpty())
			return false;
//		contentList.add(url);
		contentList.offer(url);
		if(isRunning==false){
			synchronized(lockObj){
				if(isRunning==false){
					isRunning=true;
					thread=new FetchThread();
					thread.start();
				}
			}
		}
		return true;
	}
	public void parse(String url) {
		Document doc=crawler.getDocument(url, "utf8");
		Elements eles=doc.getElementsByClass("title");
		NewsContent news=new NewsContent();
		if(eles.isEmpty())
			return ;
		Element titleEle=eles.get(0);
		String titleStr=titleEle.text();
		news.setTitle(titleStr);
//		System.out.println("title:"+titleStr);
		Element dataEle=(Element)titleEle.parent().nextSibling().nextSibling();
		String dataStr=dataEle.text();
		analyzeData(dataStr,news);
		Elements celes=doc.getElementsByClass("content");
		if(celes.isEmpty())
			return ;
		StringBuilder sb=new StringBuilder("");
		Elements contents=celes.get(0).children();
		for(Element e:contents){
			sb.append(e.text());
		}
		String contentStr=sb.toString();
		contentStr=contentStr.replaceAll("'", "\'");
//		System.out.println("content:"+contentStr);
		news.setContent(contentStr);
		newsDao.insertNewsContent(news);
	}
	private void analyzeData(String dataStr,NewsContent news){
		//dataStr=dataStr.replaceAll("\\s{1,}"," ");
		String[] strs=dataStr.split("����");
//		System.out.println("url:"+strs[0]);
//		System.out.println("date:"+strs[1]);
//		System.out.println("src:"+strs[2].substring(strs[2].indexOf(":")));
//		System.out.println("count:"+strs[3].substring(strs[3].indexOf(":"), strs[3].lastIndexOf(" ")));
		news.setUrl(strs[0]);
		news.setDate(strs[1]);
		news.setSrc(strs[2].substring(strs[2].indexOf(":")+1));
		int count=Integer.parseInt(strs[3].substring(strs[3].indexOf(":")+1, strs[3].lastIndexOf(" ")).trim());
		news.setCount(count);
	}

}
