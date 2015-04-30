package com.wys.data;
import java.sql.*;
public interface DataIface {
	public void getConnection();
	public boolean update(String command);
	public ResultSet get(String command);
}
