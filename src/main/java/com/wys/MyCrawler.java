package com.wys;

import org.jsoup.Connection.KeyVal;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;

import com.wys.data.DataIface;
import com.wys.data.MySQLTool;
import com.wys.data.MySqlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
public class MyCrawler {
	List<String> list=new LinkedList<String>();
	Set<String> visitedSet=new TreeSet<String>();
	private static DataIface tool=new MySqlFactory().getInstance();
	public Connection connect(String html){
		Connection con=null;
		try{
			con=Jsoup.connect(html);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("connect to "+html+" failed");
		}
		list.add(html);
		visitedSet.add(html);
		return con;
	}
	public Connection connect(String url,Collection<Connection.KeyVal> data){
		Connection con=null;
		con=Jsoup.connect(url).data(data);
		return con;
	}
	public void parse(boolean isGet){
		Connection con=null;
		Document doc=null;
		int count=0;
		while(!list.isEmpty()&&count++<100){
			String url=list.remove(0);
			System.out.println(url);
			con=Jsoup.connect(url);
			try{
				if(isGet)
					doc=con.get();
				else
					doc=con.post();
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("get data failed");
			}
			Elements eles=doc.select("a[href]");
			for(Element ele:eles){
				String newUrl=ele.attr("abs:href");
				if(newUrl.equals(""))
					continue;
				System.out.println(newUrl);
				if(!visitedSet.contains(newUrl)){
					visitedSet.add(newUrl);
					list.add(newUrl);
					insertRecord(newUrl);
				}else{
					updateRecord(newUrl);
				}
			}
		}	
	}
	private boolean insertRecord(String url){
		String command="insert into pagerecord (name,count) values('"+url+"',1)";
		return tool.update(command);
	}
	private boolean updateRecord(String url){
		String command="update pagerecord set count=count+1 where name='"+url+"'";
		return tool.update(command);
	}
	public void simpleOneFetchVisit(Connection con,boolean isGet){
		Document doc=null;
		try{
			if(isGet)
				doc=con.get();
			else
				doc=con.post();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("get data failed");
		}
		getInnerHtml(doc);
	}
	private void getInnerHtml(Document doc){
		String innerHtml=doc.html();
		System.out.println(innerHtml);
	}
	public static void main(String[] args){
		String url="http://www.baidu.com/";
		MyCrawler crawler=new MyCrawler();
		Connection con=crawler.connect(url);
//		crawler.simpleOneFetchVisit(con, true);
		crawler.parse(true);
		//crawler.parse(crawler.connect(url),true);
	}
}
