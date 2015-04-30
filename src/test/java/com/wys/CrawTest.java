package com.wys;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.wys.CrawlerFactory;
public class CrawTest {
	private static CrawlerIface crawler=CrawlerFactory.getInstance();
	@Test
	public void myTest(){
		String url="http://oldssdut.dlut.edu.cn/index.php/News/student.html";
		Document doc=crawler.getDocument(url, "utf8");
		System.out.print(doc.html());
	}
}
