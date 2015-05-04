package com.wys;

import org.junit.Test;

import java.sql.*;

import com.wys.anotherdata.AbstractDataOperate;
import com.wys.anotherdata.DataOperate;
import com.wys.anotherdata.DataOperateFactory;
import com.wys.anotherdata.DataType;
import com.wys.data.DataFactory;
import com.wys.data.DataIface;
import com.wys.data.MySQLTool;
import com.wys.data.MySqlFactory;

public class MySQLTest {
	private static DataFactory factory=new MySqlFactory();
	private static DataIface tool=factory.getInstance();
	private static AbstractDataOperate dataSource=DataOperateFactory.getInstance(DataType.MySQL);
	public void sqlTest(){
		String command="select * from event;";
		ResultSet res=tool.get(command);
		try{
			while(res.next()){
				System.out.println(res.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void sqlTest2(){
		try{
			dataSource.connect();
			dataSource.insert("insert into pagerecord (name,count) values ('wangyongshan',100)");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
