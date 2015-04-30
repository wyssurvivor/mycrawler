package com.wys.anotherdata;
import java.sql.*;
public abstract class AbstractDataOperate implements DataOperate{
	protected Connection con;
	public abstract void connect();
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
