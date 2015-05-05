package com.wys.anotherdata;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MySQLDataOperate extends AbstractDataOperate{
	public MySQLDataOperate(){
		this.connect();
	}
	public boolean insert(String command) {
		Statement stat=null;
		try {
			stat=con.createStatement();
			int rowsEffected=stat.executeUpdate(command);
			if(rowsEffected>0)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(command);
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(String command){
		Statement stat=null;
		try {
			stat=con.createStatement();
			int rowsEffected=stat.executeUpdate(command);
			if(rowsEffected>0)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet get(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connect() {
		Properties prop=new Properties();
		InputStream in=Object.class.getResourceAsStream("/sqlServer.properties");
		String user=null;
		String pwd=null;
		String url=null;
		try{
			prop.load(in);
			String driver=prop.getProperty("jdbc.driver");
			Class.forName(driver);
			url=prop.getProperty("jdbc.url");
			user=prop.getProperty("jdbc.user");
			pwd=prop.getProperty("jdbc.pwd");
			con=DriverManager.getConnection(url, user, pwd);	
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

}
