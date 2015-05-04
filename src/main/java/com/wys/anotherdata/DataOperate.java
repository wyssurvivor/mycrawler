package com.wys.anotherdata;
import java.sql.*;
public interface DataOperate {
	public boolean insert(String command);
	public boolean update(String command); 
	public ResultSet get(String command);
}
