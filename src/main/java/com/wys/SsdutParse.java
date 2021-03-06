package com.wys;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SsdutParse implements ParseStrategy{
	String rootUrl="";
	private List<String> indexList=new LinkedList<String>();
	private Set<String> visitedSet=new TreeSet<String>();
	private static CrawlerIface crawler=CrawlerFactory.getInstance();
	private ContentFetch fetcher=new ContentFetch();
	public SsdutParse(){
	}
	private void initParse(String url){
		rootUrl=url;
		indexList.add(rootUrl);
		visitedSet.add(rootUrl);
	}
	public void parse(String url){
		initParse(url);
		String baseUrl="http://oldssdut.dlut.edu.cn";
		while(!indexList.isEmpty()){
			String indexUrl=indexList.remove(0);
			Document doc=crawler.getDocument(indexUrl, "utf8");
			Elements eles=doc.select("a[href]");
			for(Element ele:eles){
				String newUrl=ele.attr("href");
				newUrl=baseUrl+newUrl;
				if(visitedSet.contains(newUrl))
					continue;
				visitedSet.add(newUrl);
				if(isContentPage(newUrl)){
					insertContentPage(newUrl);
				}else if(isIndexPage(newUrl)&&newUrl.indexOf("News/student")>0){
					insertIndexPage(newUrl);
				}else 
					continue;
			}
		}
	}
	public void insertIndexPage(String newUrl){
		if(newUrl.indexOf("?&p=")>0){
			String[] strs=newUrl.split("[0-9]+\\?&p=");
			newUrl=strs[0]+strs[1];
		}
		indexList.add(newUrl);
	}
	private void insertContentPage(String newUrl){
		fetcher.insertContentPage(newUrl);
	}
	private boolean isIndexPage(String url){
		Pattern pattern=Pattern.compile("(http://|https://){1}[\\w\\./]+(\\?&p=[0-9]*)?");
		Matcher matcher=pattern.matcher(url);
		return matcher.matches();
	}
	private boolean isContentPage(String url){
		Pattern pattern=Pattern.compile("(http://|https://){1}[\\w\\./]+([0-9]+\\.html){1}");
		Matcher matcher=pattern.matcher(url);
		return matcher.matches();
	}
	public static void main(String[] args){
		String url="http://oldssdut.dlut.edu.cn/index.php/News/student/p/2";
		SsdutParse parse=new SsdutParse();
		parse.parse(url);
	}
}
