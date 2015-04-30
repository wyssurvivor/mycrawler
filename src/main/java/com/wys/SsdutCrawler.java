package com.wys;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
public class SsdutCrawler implements CrawlerIface{

	public Document getDocument(String url) {
		Connection con=Jsoup.connect(url);
		Document doc=null;
		try{
			doc=con.get();
		}catch(IOException e){
			e.printStackTrace();
		}
		return doc;
	}

	public Document getDocument(String url, String charCode) {
		String html=readHtml(url,charCode);
		return Jsoup.parse(html);
	}
	
	private String readHtml(String url,String charCode){
		StringBuffer sb=new StringBuffer("");
		URL url_1=null;
		try{
			url_1=new URL(url);
			BufferedReader br=new BufferedReader(new InputStreamReader(url_1.openStream(),charCode));
			String s="";
			while((s=br.readLine())!=null){
				sb.append(s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
}
