package com.wys.anotherdata;
import java.sql.*;
public interface DataOperate {
	public void insert(String command);
	public ResultSet get(String command);
}
