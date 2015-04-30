package com.wys.data;
import java.util.Properties;
import java.io.*;
import java.nio.*;
import java.sql.*;
public class MySQLTool implements DataIface {
	private Connection con=null;
	
	public void getConnection(){
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
	
	public ResultSet get(String command){
		try{
			Statement stat=con.createStatement();
			ResultSet res=stat.executeQuery(command);
			return res;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
		try {
			con.close();
			con=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
