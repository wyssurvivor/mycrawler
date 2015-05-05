package com.wys.dao;

import com.wys.anotherdata.AbstractDataOperate;
import com.wys.anotherdata.DataOperateFactory;
import com.wys.anotherdata.DataType;
import com.wys.bean.NewsContent;
public class NewsContentDAO {
	private static AbstractDataOperate dataSource=DataOperateFactory.getInstance(DataType.MySQL);
	public boolean insertNewsContent(NewsContent news){
		String str="insert into ssdutparse (sp_title,sp_url,sp_date,sp_src,sp_count,sp_content) values ('"
				+news.getTitle()+"','"+news.getUrl()+"','"+news.getDate()+"','"+news.getSrc()+"','"+news.getCount()
				+"','"+news.getContent()+"')";
		return dataSource.insert(str);	
	}
}
