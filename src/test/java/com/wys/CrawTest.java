package com.wys;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.wys.CrawlerFactory;
public class CrawTest {
	private static CrawlerIface crawler=CrawlerFactory.getInstance();
	public void myTest(){
		String url="http://oldssdut.dlut.edu.cn/index.php/News/student.html";
		Document doc=crawler.getDocument(url, "utf8");
		System.out.print(doc.html());
	}
	@Test
	public void testStringSplit(){
		String newUrl="http://oldssdut.dlut.edu.cn/index.php/News/student/p/12?&amp;p=2";
		if(newUrl.indexOf("?&amp;p=")>0){
			String[] strs=newUrl.split("[0-9]+\\?&amp;p=");
			newUrl=strs[0]+strs[1];
		}
		System.out.println(newUrl);
	}
//	@Test
	public void testPattern(){
		String url="http://oldssdut.dlut.edu.cn/index.php/News/student.html";
		SsdutParse parse=new SsdutParse();
		parse.parse(url);
//		System.out.println(parse.isContentPage(url));
	}
	public void testIndexPage(){
		String url="http://oldssdut.dlut.edu.cn/index.php/News/student.html?&p=2";
		SsdutParse parse=new SsdutParse();
//		System.out.println(parse.isIndexPage(url));
	}
	public void testParseContent(){
		String url="http://oldssdut.dlut.edu.cn/index.php/News/13274.html";
		ContentFetch fetcher=new ContentFetch();
		fetcher.parse(url);
	}
}
