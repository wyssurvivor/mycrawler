package com.wys.data;

public class MySqlFactory implements DataFactory{
	protected static DataIface tool=null;
	
	public DataIface getInstance(){
		if(tool==null){
			tool=new MySQLTool();
			tool.getConnection();
		}
		return tool;
	}
}
