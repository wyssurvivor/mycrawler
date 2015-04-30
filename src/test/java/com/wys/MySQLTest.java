package com.wys;

import org.junit.Test;

import java.sql.*;

import com.wys.data.DataFactory;
import com.wys.data.DataIface;
import com.wys.data.MySQLTool;
import com.wys.data.MySqlFactory;

public class MySQLTest {
	private static DataFactory factory=new MySqlFactory();
	private static DataIface tool=factory.getInstance();
	@Test
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
}
